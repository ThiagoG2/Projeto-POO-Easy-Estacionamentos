package br.ufrpe.easyestacionamento.negocio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

import br.ufrpe.easyestacionamento.dados.RepositorioEstacionamentos;
import br.ufrpe.easyestacionamento.dados.RepositorioRegistroEstacionamento;
import br.ufrpe.easyestacionamento.dados.RepositorioVeiculos;
import br.ufrpe.easyestacionamento.negocio.beans.RegistroEstacionamento;
import br.ufrpe.easyestacionamento.negocio.beans.Estacionamento;
import br.ufrpe.easyestacionamento.negocio.beans.Veiculo;
import br.ufrpe.easyestacionamento.negocio.exception.EstacionamentoErradoException;
import br.ufrpe.easyestacionamento.negocio.exception.EstacionamentoSemVagasException;
import br.ufrpe.easyestacionamento.negocio.exception.VeiculoJaEstacionadoException;
import br.ufrpe.easyestacionamento.negocio.exception.VeiculoNaoEstacionadoException;
import br.ufrpe.easyestacionamento.negocio.exception.VeiculoNaoExisteException;

public class CadastroRegistroEstacionamento {
	private RepositorioRegistroEstacionamento<RegistroEstacionamento> repositorioRegistroEstacionamento;
	private RepositorioVeiculos<Veiculo> repositorioVeiculos;
	private RepositorioEstacionamentos<Estacionamento> repositorioEstacionamento;

	public CadastroRegistroEstacionamento() {
		this.repositorioRegistroEstacionamento = RepositorioRegistroEstacionamento.getInstance();
		this.repositorioVeiculos = RepositorioVeiculos.getInstance();
		this.repositorioEstacionamento = RepositorioEstacionamentos.getInstance();
	}

	public void entrada(String placa, Estacionamento estacionamento)
			throws VeiculoNaoExisteException, EstacionamentoSemVagasException, VeiculoJaEstacionadoException {
		Veiculo v = repositorioVeiculos.buscar(placa);

		if (v == null) {
			throw new VeiculoNaoExisteException("Veículo não encontrado");
		} else {
			if (!existe(v.getPlaca())) {
				if (repositorioEstacionamento.getVagas(estacionamento) > 0) {
					RegistroEstacionamento estacionar = new RegistroEstacionamento(v, LocalDateTime.now());
					this.repositorioRegistroEstacionamento.cadastrar(estacionar);
					this.repositorioEstacionamento.entrada(estacionar, estacionamento);
					this.repositorioEstacionamento.setVagas(estacionamento,
							repositorioEstacionamento.getVagas(estacionamento) - 1);
				} else {
					throw new EstacionamentoSemVagasException("Estacionamento sem vagas, não é possível cadastrar");
				}
			} else {
				throw new VeiculoJaEstacionadoException("Veículo já estacionado!");
			}
		}
	}

	public double saida(String placa, Estacionamento estacionamento)
			throws VeiculoNaoEstacionadoException, EstacionamentoErradoException {
		RegistroEstacionamento e = this.repositorioRegistroEstacionamento.buscar(placa);
		if (e != null && e.getSaida() == null) {
			this.repositorioEstacionamento.saida(e, estacionamento);
			this.repositorioEstacionamento.setVagas(estacionamento,
					repositorioEstacionamento.getVagas(estacionamento) + 1);
			this.repositorioRegistroEstacionamento.remover(e);
			if(e.getVeiculo().getCliente().isMensalista()) {
				if(!e.getVeiculo().getCliente().isPagamentoMensalista()) {
					e.getVeiculo().getCliente().setPagamentoMensalista(true);
					this.repositorioEstacionamento.setCaixa(100, estacionamento);
				}
				return 0;
			}else {
			e.setSaida(LocalDateTime.now());
			double valorEstacionamento = calcularValorEstacionamento(e.getEntrada(), e.getSaida());
			this.repositorioEstacionamento.setCaixa(valorEstacionamento, estacionamento);
			return valorEstacionamento;
			}
		} else {
			throw new VeiculoNaoEstacionadoException("Não existe veículo estacionado com essa placa");
		}
	}

	private double calcularValorEstacionamento(LocalDateTime entrada, LocalDateTime saida) {
		double valorEstacionamento;

		Period periodo = getPeriod(entrada, saida);
		long time[] = getTime(entrada, saida);

		if (periodo.getDays() >= 1) {
			valorEstacionamento = periodo.getDays() * 20;
		} else if (time[0] >= 1) {
			valorEstacionamento = time[0] * 8;
		} else {
			valorEstacionamento = 5;

		}

		return valorEstacionamento;
	}

	private static final int MINUTES_PER_HOUR = 60;
	private static final int SECONDS_PER_MINUTE = 60;
	private static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

	private static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
		return Period.between(dob.toLocalDate(), now.toLocalDate());
	}

	private static long[] getTime(LocalDateTime dob, LocalDateTime now) {
		LocalDateTime today = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), dob.getHour(),
				dob.getMinute(), dob.getSecond());
		Duration duration = Duration.between(today, now);

		long seconds = duration.getSeconds();

		long hours = seconds / SECONDS_PER_HOUR;
		long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
		long secs = (seconds % SECONDS_PER_MINUTE);

		return new long[] { hours, minutes, secs };
	}

	private boolean existe(String placa) {
		return this.repositorioRegistroEstacionamento.existe(placa);
	}

}
