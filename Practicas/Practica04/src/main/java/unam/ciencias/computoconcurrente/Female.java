package unam.ciencias.computoconcurrente;

public class Female extends Participant {

    public Female(Toilette toilette) {
        super(toilette);
    }

    @Override
    public void enterToilette() {
        this.toilette.enterFemale();
    }

    @Override
    public void leaveToilette() {
        this.toilette.leaveFemale();
    }
}
