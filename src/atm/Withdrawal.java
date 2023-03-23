package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {

    JTextField amount ;
    JButton withdraw , back ;
    String pinNumber ;

    public Withdrawal(String pinNumber){

        this.pinNumber = pinNumber ;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg")) ;
        Image i2 = i1.getImage().getScaledInstance(900 , 900 , Image.SCALE_DEFAULT) ;
        ImageIcon i3 = new ImageIcon(i2) ;
        JLabel image = new JLabel(i3) ;
        image.setBounds(0 , 0 , 900 , 650);
        add(image) ;

        JLabel text = new JLabel("Enter the amount you want to withdraw") ;
        text.setBounds(200 , 180 , 700 , 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System" , Font.BOLD , 16));
        image.add(text) ;

        amount = new JTextField() ;
        amount.setFont(new Font("System" , Font.BOLD , 16));
        amount.setBounds(200 , 220 , 200 , 30);
        image.add(amount) ;

        withdraw = new JButton("Withdraw") ;
        withdraw.setBounds(365 , 362 , 145 , 30);
        withdraw.addActionListener(this);
        image.add(withdraw) ;

        back = new JButton("Back") ;
        back.setBounds(365 , 397 , 145 , 27);
        back.addActionListener(this);
        image.add(back) ;

        setSize(900 , 700);
        setLocation(200 , 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == withdraw){
            String number = amount.getText() ;
            Date date = new Date() ;
            if(number.isEmpty()){
                JOptionPane.showMessageDialog(null , "Withdrawal amount not entered");
            }
            else{
                try {
                    Conn conn = new Conn() ;
                    String query = "insert into bank values('"+ pinNumber+"' , '"+date+"' , '"+number+"' , 'Withdraw')" ;
                    conn.s.executeUpdate(query) ;
                    JOptionPane.showMessageDialog(null , "Amount of rs. "+ number + " withdrawn");
                    setVisible(false);
                    new Transactions(pinNumber).setVisible(true);
                }
                catch (Exception e){
                    e.printStackTrace() ;
                }
            }
        }
        else if(ae.getSource() == back){
            setVisible(false);
            new Transactions(pinNumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Withdrawal("") ;
    }
}
