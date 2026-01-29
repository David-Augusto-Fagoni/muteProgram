package view;

import controller.MuteController;
import model.Processo;

public class Principal {

    public static void main(String[] args) throws Exception {

        MuteController muteC = new MuteController();
        Processo p = new Processo();
        String statusAnterior = "";

        while (true) {
            String statusAtual = muteC.pegarStatus();
            if (statusAtual == null) {
                Thread.sleep(200);
                continue;
            }

            if (!statusAtual.equals(statusAnterior)) {

                if ("AD".equals(statusAtual)) {
                    p.mute();
                } else if ("MUSIC".equals(statusAtual)) {
                    p.unmute();
                }
 
                statusAnterior = statusAtual;
            }
        }

    }
}
