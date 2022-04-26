package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Game extends JFrame {
    private JButton[] bt = new JButton[9];
    private JLabel placar = new JLabel("Placar");
    private JLabel px = new JLabel("X 0");
    private JLabel po = new JLabel("O 0");

    private boolean xo = false;
    private boolean click[] = new boolean[9];

    private int countClick = 0;
    private int pontosX = 0;
    private int pontosO = 0;

    public Game(){
        setVisible(true);
        setTitle("Jogo da velha");
        setDefaultCloseOperation(3);
        setLayout(null);
        setBounds(0,0,600,600);
        buttons();
        initClick();
        addPlacar();
        update();
    }
    public static void main(String[] args) {
        new Game();
    }

    private void buttons(){
        int count = 0;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                bt[count] = new JButton();
                add(bt[count]);
                bt[count].setBounds((100 * i) + 150, (100*j)+120, 100, 100);
                bt[count].setFont(new Font("Arial", Font.BOLD, 40));
                count++;
            }
        }
    }

    private void addPlacar(){
        add(placar);
        add(px);
        add(po);

        placar.setBounds(290,50,100,30);
        px.setBounds(250,70,100,30);
        po.setBounds(350,70,100,30);
    }

    private void initClick(){
        for(int i = 0; i<9; i++){
            click[i] = false;
            bt[i].setText("");
            countClick = 0;
        }
    }

    private void update(){
        for(int i = 0; i<9; i++) {
            joga(bt[i], i);
        }
    }

    private void muda(JButton bt){
        if (xo) {
            bt.setText("O");
        } else {
            bt.setText("X");
        }
        xo = !xo;
    }

    private void joga(JButton bt, int index){
        bt.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!click[index]){
                    click[index] = true;
                    muda(bt);
                    countClick ++;
                }
                ganhou();
            }
        });
    }

    private void atualizaPlacar(){
        px.setText("X " + pontosX);
        po.setText("O " + pontosO);
    }

    private void ganhou(){
        if(vLinha("X") || vColuna("X") || vDiagonal("X")) {
            JOptionPane.showConfirmDialog(null, "X ganhou");
            pontosX++;
            initClick();
        }else  if(vLinha("O") || vColuna("O") || vDiagonal("O")) {
            JOptionPane.showConfirmDialog(null, "O ganhou");
            pontosO++;
            initClick();
        }
        else if(countClick == 9){
            JOptionPane.showConfirmDialog(null, "Empatou");
            initClick();
        }
        atualizaPlacar();
    }

    private boolean vDiagonal(String str) {
        return  (bt[0].getText().equals(str) && bt[4].getText().equals(str) && bt[8].getText().equals(str) ||
                 bt[2].getText().equals(str) && bt[4].getText().equals(str) && bt[6].getText().equals(str));
    }

    private boolean vColuna(String str) {
        return (bt[0].getText().equals(str) && bt[3].getText().equals(str) && bt[6].getText().equals(str) ||
               (bt[1].getText().equals(str) && bt[4].getText().equals(str) && bt[7].getText().equals(str) ||
                bt[2].getText().equals(str) && bt[5].getText().equals(str) && bt[8].getText().equals(str)));
    }

    private boolean vLinha(String str) {
        return (bt[0].getText().equals(str) && bt[1].getText().equals(str) && bt[2].getText().equals(str) ||
               (bt[3].getText().equals(str) && bt[4].getText().equals(str) && bt[5].getText().equals(str) ||
                bt[6].getText().equals(str) && bt[7].getText().equals(str) && bt[8].getText().equals(str)));

    }
}
