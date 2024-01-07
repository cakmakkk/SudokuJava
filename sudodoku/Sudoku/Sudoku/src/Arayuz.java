import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Arayuz extends Sudoku {
    ImageIcon resim = new ImageIcon("Sudoku/image/board.png");
    ImageIcon icon = new ImageIcon("Sudoku/image/giris.jpg");
    File file = new File("Sonuçlar.txt");
    JFrame framee = new JFrame();
    JLabel label = new JLabel();
    JLabel label1 = new JLabel();
    JMenu menu = new JMenu("Yeni Oyun");
    JMenuBar menuBar = new JMenuBar();
    JButton[][] buton ;
    JButton[] degerler;
    private int degerTutucu;
    private int[][] sayılar;
    JLabel zamanTablosu;
    Timer zamanlayici;
    int kalanZaman ;
    int tumZaman ;
     String zorlukTutucu;
    Arayuz(String zorlukTutucu){
        super(zorlukTutucu);
        this.zorlukTutucu=zorlukTutucu;

        buton = new JButton[9][9];
        degerler= new JButton[9];
        sayılar = new int[9][9];

        menu();
        tahtadakiSayilar();
        degerler();

        baslaGeriSayim();
        ekran();
    }
    private void ekran(){
        framee.setIconImage(icon.getImage());
        label.setBounds(35,-32,800,800);
        label.setIcon(resim);

        label1.setBounds(-60, 0, 2000, 2000);
        label1.setLayout(null);

        JLabel backgroundLabel = new JLabel(new ImageIcon("Sudoku/image/background.jpg"));
        backgroundLabel.setBounds(60, 0, 2000, 2000);



        framee.setLayout(null);
        framee.setMinimumSize(new Dimension(1000,800));
        framee.setBounds(250,11,1000,500);

        framee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label.add(backgroundLabel);
        label1.add(backgroundLabel);

        framee.add(label);
        framee.add(label1);


    }
    private void degerler(){
        for (int x = 0 ;x<9;x++){

            degerler[x] = new JButton();
            degerler[x].setBounds(820,60+x*70,50,50);
            String str = String.valueOf(x+1);
            degerler[x].setText(str);
            degerler[x].setBorderPainted(false);
            degerler[x].setBackground(new Color(2,200,180));
            degerler[x].setFont(new Font ("sea",Font.BOLD,20));

            int finalX = x;
            degerler[x].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int k =0;k<9;k++){
                        if (k==finalX){
                            degerTutucu=finalX+1;
                            degerler[k].setBackground(Color.orange);}
                        else {
                            degerler[k].setBackground(new Color(2,200,180));}
                    }
                }
            });
            framee.getContentPane().add(degerler[x]);
            framee.add(degerler[x]);
        }
    }
    private void tahtadakiSayilar(){
        for (int y = 0;y<9;y++){
            for (int x=0;x <9;x++){

                buton[x][y] = new JButton();
                buton[x][y].setBackground(Color.white);
                buton[x][y].setBounds(41+85*x,40+74*y,80,68);
                buton[x][y].setBorderPainted(false);
                buton[x][y].setFont(new Font ("font",Font.BOLD,30));
                sayılar[x][y]=getBoard1()[x][y];
                buton[x][y].setText(kutuya(x,y));
                buton[x][y].setOpaque(false);
                framee.add(buton[x][y]);

                int finalY = y; // ActionListener için final olması gerekiyor
                int finalX = x;
                buton[x][y].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int y = 0;y<9;y++){
                            for (int x=0;x <9;x++){
                                if (sayılar[x][y]!=getBoard()[x][y]){
                                    if (y==finalY && x==finalX){
                                        String str =String.valueOf(degerTutucu);
                                        buton[x][y].setText(str);
                                        sayılar[x][y]=degerTutucu;
                                    }
                                    if (sayılar[x][y]!=getBoard()[x][y]){
                                        buton[x][y].setForeground(Color.red);
                                        buton[x][y].setForeground(Color.red);
                                    }
                                    else {
                                        buton[x][y].setForeground(new Color(0,190,0));
                                    }
                                    if(kontrol()){
                                        framee.dispose();
                                        int minutes = (tumZaman / 60)-(kalanZaman / 60);
                                        int seconds = (tumZaman % 60)-(kalanZaman % 60)+1;
                                        String timeString = String.format("%02d:%02d", minutes, seconds);
                                        BitisEkrani bitisEkrani = new BitisEkrani(timeString);
                                        zamanlayici.stop();
                                       bitisEkrani.basarili();}
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    private void baslaGeriSayim() {
        zamanTablosu = new JLabel("Kalan Süre: 10:00");
        zamanTablosu.setBounds(820, 20, 150, 30);
        zamanTablosu.setFont(new Font("Arial", Font.BOLD, 16));

       switch (zorlukTutucu){
           case "kolay":
               kalanZaman = 5;
               tumZaman = 50;
               zamanTablosu = new JLabel("Kalan Süre: 03:00");
               framee.add(zamanTablosu);
      break;
           case "orta":

               kalanZaman = 299;
               tumZaman = 299;
               zamanTablosu = new JLabel("Kalan Süre: 05:00");
               framee.add(zamanTablosu);
      break;
           case "zor":
               kalanZaman = 479;
               tumZaman = 479;
               zamanTablosu = new JLabel("Kalan Süre: 08:00");
               framee.add(zamanTablosu);
    break;
       }
        zamanTablosu.setBounds(820, 20, 150, 30);
        zamanTablosu.setFont(new Font("Arial", Font.BOLD, 16));
        framee.add(zamanTablosu);

        zamanlayici = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kalanZaman--;
                int minutes = kalanZaman / 60;
                int seconds = kalanZaman % 60;
                String timeString = String.format("%02d:%02d", minutes, seconds);
                zamanTablosu.setText("Kalan Süre: " + timeString);
                if (kalanZaman <= 0) {
                    zamanlayici.stop();
                    JOptionPane.showMessageDialog(null, "Zaman doldu oyun burda biter!..");

                    framee.dispose();
                    BitisEkrani bitisEkrani = new BitisEkrani(timeString);
                    bitisEkrani.basarisiz();
                }
            }
        });
        zamanlayici.start();
    }
    private boolean kontrol(){
        int a=0;
        for (int y = 0;y<9;y++){
            for (int x=0;x <9;x++){
                if (sayılar[x][y]==getBoard()[x][y])
                    a++;
            }
        }
        if (a==81)
            return true;
        else {
            return false;
        }
    }
    public void setVisible(boolean a){
        framee.setVisible(a);
    }


    public void menu(){
   JMenuItem Yeeni = new JMenuItem("Yeni oyun");
    menu.add(Yeeni);
    Yeeni.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==Yeeni){
           zamanlayici.stop();
            framee.dispose();
            GirisEkrani girisEkrani =new GirisEkrani();
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            try (FileWriter writer = new FileWriter(file,true)) {
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(" - Yeni oyuna geçildi ");
                bufferedWriter.close();
            }
            catch (IOException a) {
                a.printStackTrace();
            }
        }
    }
});
    menuBar.add(menu);
        framee.setJMenuBar(menuBar);
    }

}




