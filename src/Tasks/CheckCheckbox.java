package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckCheckbox {
    public static void checkCheckbox(JCheckBox checkbox, JFrame frame) {
        checkbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(itemEvent.getStateChange()==1){
                    JDialog d = new JDialog(frame, "Confirm", true);
                    JButton yer = new JButton("Confirm");
                    JButton naw = new JButton("naw");
                    yer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            TasksDatabase.deleteTask(checkbox.getText().split(" date:")[0]);
                            checkbox.setVisible(false);
                            d.setVisible(false);
                        }
                    });
                    naw.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            d.setVisible(false);
                        }
                    });
                    d.setLayout(new FlowLayout());
                    d.setBounds((frame.getWidth()/2)-150,(frame.getHeight()/2)-50,300,100);
                    d.add(new Label("Are you sure this task is fully complete?"));
                    d.add(yer);
                    d.add(naw);
                    d.setVisible(true);
                }
            }
        });
    }
}
