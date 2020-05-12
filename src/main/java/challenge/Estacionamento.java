package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private final int VAGAS_ESTACIONAMENTO = 10;
    private final int LIMITE_PONTOS = 20;
    private final int IDADE_BENEFICIADA = 55;
    private List<Carro> carrosNoEstacionamento = new ArrayList<Carro>();


    public void estacionar(Carro carro) {

        Motorista motorista = carro.getMotorista();

        if (motorista == null) {
            throw new EstacionamentoException("Não pode ter carro sem motorista!!!");
        }

        if (!isMaiorIdade(motorista.getIdade())) {
            throw new EstacionamentoException("O motorista não pode ser menor de idade!!!");
        }

        if (motorista.getPontos() == 0) {
            throw new EstacionamentoException("O motorista não pode estar com habilitação zerada!!!");
        } else if (motorista.getPontos() > LIMITE_PONTOS) {
            throw new EstacionamentoException("O motorista não pode estar com habilitação zerada!!!");
        }

        //Se estacionamento cheio verificar idade a partir do primeiro carro e remover os motoristas com idade inferior a 55
        if (carrosEstacionados() == VAGAS_ESTACIONAMENTO) {
            removerCarroEstacionado();
        }

        carrosNoEstacionamento.add(carro);
    }

    private void removerCarroEstacionado() {
        for (int i = 0; i < carrosNoEstacionamento.size(); i++) {
            if (carrosNoEstacionamento.get(i).getMotorista().getIdade() < IDADE_BENEFICIADA) {
                carrosNoEstacionamento.remove(i);
                break;
            }
        }

        if (carrosEstacionados() == 10) {
            throw new EstacionamentoException("Não é possivel superar o limite do estacionamento!!!");
        }
    }

    public int carrosEstacionados() {
        return carrosNoEstacionamento.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carrosNoEstacionamento.contains(carro);
    }

    private boolean isMaiorIdade(int idade) {
        return (idade >= 18) ? true : false;
    }
}
