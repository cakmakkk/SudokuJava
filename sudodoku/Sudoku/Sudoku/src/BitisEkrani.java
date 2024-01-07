import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class BitisEkrani  implements ActionListener,Bitis {
    File file = new File("Sonuçlar.txt");
    JFrame frame = new JFrame("Sudoku");
    ImageIcon icon = new ImageIcon("Sudoku/image/sas.png");
    ImageIcon basariliImage = new ImageIcon("Sudoku/image/basarili.png");
    ImageIcon iconic = new ImageIcon("Sudoku/image/giris.jpg");
    JLabel label = new JLabel();
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    static String zaman ;
    JButton buton = new JButton("Yeni oyun");

    BitisEkrani(String zaman){

        this.zaman=zaman;
        ekran();
    }
    public void ekran(){
        frame.setIconImage(iconic.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(400,400));
        frame.setBounds(400,75,800,700);
        frame.setVisible(true);


        buton.setBounds(340,480,130,30);

        buton.addActionListener(this);

        frame.add(buton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==buton){
            frame.setVisible(false);
            frame.dispose();
            GirisEkrani girisEkrani = new GirisEkrani();
        }
    }
    @Override
    public void basarisiz() {
        label.setIcon(icon);
        label.setText("Süreniz doldu maalesef basarısız oldunuz");
        label.setBounds(210,30,500,420);

        label2.setBounds(-150, 0, 2000, 2000); // label1 boyutları
        label2.setLayout(null);

        JLabel backgroundLabel = new JLabel(new ImageIcon("Sudoku/image/background.jpg"));
        backgroundLabel.setBounds(60, 0, 2000, 2000);

        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setFont(new Font("MV Boli",Font.CENTER_BASELINE,20));

        label.add(backgroundLabel);
        label2.add(backgroundLabel);

        frame.add(label);
        frame.add(label2);

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println(zaman);
        try (FileWriter writer = new FileWriter(file,true)) {
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(" - "+" başarısız");
            bufferedWriter.newLine();
            bufferedWriter.close();
            writer.close();
        } catch (IOException a) {
            a.printStackTrace();
        }
    }
    @Override
    public void basarili() {
        label.setIcon(basariliImage);
        label.setBounds(170,0,500,420);
        label1.setBounds(220,415,400,30);

        label2.setBounds(0, 0, 800, 700);
        label2.setLayout(null);

        JLabel backgroundLabel = new JLabel(new ImageIcon("Sudoku/image/background.jpg"));
        backgroundLabel.setBounds(0, 0, 2000, 2000);

        label.add(backgroundLabel);
        label2.add(backgroundLabel);

        label.setText("    Tebrikler   ");
        label1.setText("      Bitirme Süreniz :  "+zaman);

        label.setHorizontalTextPosition(JLabel.CENTER);
        label1.setHorizontalTextPosition(JLabel.CENTER);

        label.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setVerticalTextPosition(JLabel.BOTTOM);

        label.setFont(new Font("MV Boli",Font.CENTER_BASELINE,20));
        label1.setFont(new Font("MV Boli",Font.CENTER_BASELINE,20));

        frame.add(label1);
        frame.add(label);
        frame.add(label2);

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println(zaman);

        try (FileWriter writer = new FileWriter(file,true)) {
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(" - " +zaman);
            bufferedWriter.newLine();
            bufferedWriter.close();
            writer.close();

        } catch (IOException a) {
            a.printStackTrace();
        }
    }
}
