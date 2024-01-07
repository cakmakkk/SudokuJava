import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
class GirisEkrani  implements ActionListener{
    String[] zorluk={"kolay","orta","zor"};
    File file = new File("Sonuçlar.txt");
    static String zorlukTutucu="kolay";
    JFrame frame = new JFrame("Sudoku");
    JTextField isim = new JTextField();
    ImageIcon icon = new ImageIcon("Sudoku/image/sas.png");
    ImageIcon iconic = new ImageIcon("Sudoku/image/giris.jpg");
    ImageIcon back = new ImageIcon("C:\\Users\\ahmet\\OneDrive\\Masaüstü\\Sudoku\\Sudoku\\image\\background.jpg");
    JLabel label = new JLabel();
    JLabel label1 = new JLabel();

    JButton buton = new JButton("Giriş");
    JComboBox zorluklar = new JComboBox(zorluk);
    String adi;
    GirisEkrani() {
        // label ve label1 oluşturuldu
        label.setIcon(icon);
        label.setBounds(210, 0, 400, 400); // label boyutları

        label1.setBounds(0, 0, 800, 700); // label1 boyutları
        label1.setLayout(new BorderLayout());

        frame.setIconImage(iconic.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(400, 75, 800, 700);
        frame.setVisible(true);

        isim.setBounds(310, 430, 150, 30);
        zorluklar.setBounds(320, 470, 130, 30);
        buton.setBounds(320, 520, 130, 30);

        zorluklar.addActionListener(this);
        buton.addActionListener(this);

        // label ve label1 arka plan resimlerini eklemek için
        JLabel backgroundLabel = new JLabel(new ImageIcon("C:\\Users\\ahmet\\OneDrive\\Masaüstü\\Sudoku\\Sudoku\\image\\background.jpg"));
        backgroundLabel.setBounds(0, 0, 800, 700); // Boyutları pencere boyutlarına göre ayarlayabilirsiniz

        label.add(backgroundLabel);
        label1.add(backgroundLabel);


        frame.add(isim);
        frame.add(buton);
        frame.add(zorluklar);
        frame.add(label);
        frame.add(label1);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==zorluklar){
            zorlukTutucu= (String) zorluklar.getSelectedItem();
            System.out.println(zorlukTutucu);
        }
        if(e.getSource()==buton){

            adi= isim.getText();

            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            adi =isim.getText();
            try {
                if (adi.isEmpty())
                    throw new IllegalArgumentException();
                Arayuz arayuz =new Arayuz(zorlukTutucu);
                frame.setVisible(false);
                arayuz.setVisible(true);
                frame.dispose();
                try (FileWriter writer = new FileWriter(file,true)) {
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.newLine();
                    bufferedWriter.write(adi+" - "+zorlukTutucu);
                    bufferedWriter.close();
                }
                catch (IOException a) {
                    a.printStackTrace();
                }
            }catch (IllegalArgumentException exception){
                System.out.println("İsim boş bırakılamaz!!!");
                JOptionPane.showMessageDialog(null, "Kullanıcı adı boş bırakılamaz");
            }
        }
}
}

