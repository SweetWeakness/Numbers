import javax.naming.CompositeName;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Display {

    private static ArrayList<Point> pointList = new ArrayList<Point>();
    private JFrame window;
    public JPanel content;
    boolean flg=false;
    long N=0;


    public void create(final int width, int height, String title){//метод для создания окна




        //окно

        window=new JFrame(title);
        JFrame.setDefaultLookAndFeelDecorated(true);
        window.setLayout(null);
        window.setPreferredSize(new Dimension(width,height));//передаем ее для нашей бумаги
        window.setResizable(false);//запрещаем изменять размер рамкипользователю
        window.pack();//чтобы рамка прогнулась под размеры бумажки
        window.setVisible(true);//банальщина - окно должно быть видимым
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//чтоб нажимая на крестик прога стопилась
        window.setLocationRelativeTo(null);// окно будет создано по центру



        //панель
        JPanel butpan=new JPanel();
        butpan.setBounds(0,0,width,height);
        butpan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        butpan.setLayout(null);
        window.getContentPane().add(butpan);








        //все кнопки и надписи
        final JButton ButAdd=new JButton("Ввести число");
        butpan.add(ButAdd);
        ButAdd.setBounds(30,75,width-60,40);

        final JTextField TextX = new JTextField();
        butpan.add(TextX);
        TextX.setBounds(30,25,width-60,40);



        final JButton ButGoI=new JButton("Перевести в систему счисления...");
        butpan.add(ButGoI);
        ButGoI.setBounds(30,125,width-60,40);
        ButGoI.setVisible(false);
        //
        final JTextField TextY = new JTextField();
        butpan.add(TextY);
        TextY.setBounds(33+(width-60)/2,125,(width-60)/4-6,40);
        TextY.setVisible(false);
        //
        final JButton ButK=new JButton("Ok");
        butpan.add(ButK);
        ButK.setBounds(30+(width-60)*3/4,125,(width-60)/4,40);
        ButK.setVisible(false);


        final JButton CheckSimple=new JButton("Проверить, простое ли число");
        butpan.add(CheckSimple);
        CheckSimple.setBounds(30,175,width-60,40);
        CheckSimple.setVisible(false);

        final JButton Go2=new JButton("Перевести в 2-ую с.c.");
        butpan.add(Go2);
        Go2.setBounds(30,250,width-60,40);
        Go2.setVisible(false);

        final JButton Back2=new JButton("Восстановить из 2-ой с.с.");
        butpan.add(Back2);
        Back2.setBounds(30,300,width-60,40);
        Back2.setVisible(false);


        final JLabel Number=new JLabel();
        butpan.add(Number);
        Number.setBounds(30,25,width-60,40);
        Number.setVisible(false);
        final JLabel Terminal=new JLabel();
        butpan.add(Terminal);
        Terminal.setBounds(30,45,width-60,40);
        Terminal.setVisible(false);

        final JLabel Lab1=new JLabel("Система счисления:");
        butpan.add(Lab1);
        Lab1.setBounds(30,125,(width-60)/2,40);
        Lab1.setVisible(false);





        //слушатели
        ButAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ButAdd.setVisible(false);
                flg=true;
                N = (!TextX.getText().equals("")?Integer.parseInt(TextX.getText()):0);
                Number.setText("Ваше введённое число:  "+N);
                TextX.setVisible(false);
                Terminal.setVisible(true);
                Number.setVisible(true);
                ButGoI.setVisible(true);
                CheckSimple.setVisible(true);
                Go2.setVisible(true);
                Back2.setVisible(true);
            }
        });



        CheckSimple.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Simple(N)){
                    Terminal.setText("Число является простым)");
                }else{
                    Terminal.setText("Число не является простым(");
                }
            }
        });

        Go2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Terminal.setText("В 2-ой системе:  "+Go2(N,3));
            }
        });

        ButGoI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ButGoI.setVisible(false);
                TextY.setVisible(true);
                ButK.setVisible(true);
                Lab1.setVisible(true);
            }
        });

        ButK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ButGoI.setVisible(true);
                TextY.setVisible(false);
                ButK.setVisible(false);
                Lab1.setVisible(false);
                int s = (!TextY.getText().equals("")?Integer.parseInt(TextY.getText()):0);
                long l=Go2(N,s);
                System.out.println(l);
                if(l==-1){
                    Terminal.setText("Ошибка!");
                }else{
                    Terminal.setText("В "+s+"-ой системе:  "+l);
                }
            }
        });



        Back2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Terminal.setText("В 10-ой системе:  "+back2(N,2));
            }
        });


    }

    //слушатели



    //все функции для решения задач
    boolean Simple(long x){
        boolean flg=true;
        for(int i=2;i<(int)Math.sqrt(x)&&flg;i++){
            if(x%i==0){
                flg=false;
            }
        }
        return flg;
    }

    long Go2(long x,int y){
        long a=1;
        if(y>0) {
            while (x != 0) {
                a = a * 10 + x % y;
                x /= y;
            }
            while (a != 0) {
                x = x * 10 + a % 10;
                a /= 10;
            }
            x=x/10;
        }else{
            x=-1;
        }
        return x;
    }

    long back2(long x, int y){
        long i=1;
        long a=0;
        if(y>0) {
            while (x != 0) {
                a += i * x %10 ;
                i *= y;
                x=x/10;
            }
            x=a;
        }else{
            x=-1;
        }
        return x;
    }




}