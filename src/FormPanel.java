import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.Key;

public class FormPanel extends JPanel {

        private JLabel nameLabel;
        private JLabel occupationLabel;
        private JTextField nameField;
        private JTextField occupationField;
        private JButton okBtn;
        private FormListener formListener;
        private JList ageList;
        private JComboBox empCombo;
        private JCheckBox ukCitizen;
        private JTextField niNumber;
        private JLabel niLabel;

        private JRadioButton maleRadio;
        private JRadioButton femaleRadio;
        private ButtonGroup genderGroup;



    public FormPanel() {

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name:");
        occupationLabel = new JLabel("Occupation:");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        ageList = new JList();
        empCombo = new JComboBox();
        ukCitizen = new JCheckBox();
        niNumber = new JTextField(10);
        niLabel = new JLabel("NI Number:");
        okBtn = new JButton("Submit");

        //setup mnemonics

        okBtn.setMnemonic(KeyEvent.VK_O);
        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);


        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");

        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");

        genderGroup = new ButtonGroup();

        maleRadio.setSelected(true);

        //setup radio buttons
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        //Checkbox setup
        niLabel.setEnabled(false);
        niNumber.setEnabled(false);

        ukCitizen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent) {
            boolean isTicked = ukCitizen.isSelected();
            niLabel.setEnabled(isTicked);
            niNumber.setEnabled(isTicked);
            }
        });

        //List setup

        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "Over 65"));

        ageList.setModel(ageModel);
        ageList.setPreferredSize(new Dimension(110, 72));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        //Combo setup

        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("Employed");
        empModel.addElement("Self-Employed");
        empModel.addElement("Un-Employed");
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);



        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
                String empCat = (String)empCombo.getSelectedItem();
                String niNum = niNumber.getText();

                String gender = genderGroup.getSelection().getActionCommand();


                FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), empCat, niNum, ukCitizen.isSelected(), gender);

                if (formListener != null) {
                    formListener.formEventOccurred(ev);
                }
            }
        });


        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }
    public void layoutComponents(){

            setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();



            /////////////First row//////////////////////////////

            gc.weightx = 1;
            gc.weighty = 0.1;

            gc.gridx = 0;
            gc.gridy = 0;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_END;
            gc.insets = new Insets(0,0,0,5);
            add(nameLabel, gc);

            gc.gridx = 1;
            gc.gridy = 0;
            gc.anchor = GridBagConstraints.LINE_START;
            gc.insets = new Insets(0,0,0,0);
            add(nameField, gc);

            ///////////////Next row///////////////////////////
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 0.1;

            gc.gridx = 0;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_END;
            gc.insets = new Insets(0,0,0,5);
            add(occupationLabel, gc);

            gc.gridx = 1;
            gc.anchor = GridBagConstraints.LINE_START;
            gc.insets = new Insets(0,0,0,0);
            add(occupationField, gc);

            //////////Next row///////////////////////////

              gc.gridy++;
            gc.weightx = 1;
            gc.weighty = 0.2;

            gc.gridx = 0;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.FIRST_LINE_END;
            gc.insets = new Insets(0,0,0,5);
            add(new JLabel("Age:"), gc);


            gc.gridx = 1;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            gc.insets = new Insets(0,0,0,0);
            add(ageList, gc);

            //////////Next row///////////////////////////

            gc.gridy++;
            gc.weightx = 1;
            gc.weighty = 0.2;

            gc.gridx = 0;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.FIRST_LINE_END;
            gc.insets = new Insets(0,0,0,5);
            add(new JLabel("Employment:"), gc);

            gc.gridx = 1;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            gc.insets = new Insets(0,0,0,0);
            add(empCombo, gc);

            //////////Next row///////////////////////////

            gc.gridy++;
            gc.weightx = 1;
            gc.weighty = 0.2;

            gc.gridx = 0;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.FIRST_LINE_END;
            gc.insets = new Insets(0,0,0,5);
            add(new JLabel("UK Citizen:"), gc);

            gc.gridx = 1;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            gc.insets = new Insets(0,0,0,0);
            add(ukCitizen, gc);

            //////////Next row///////////////////////////

            gc.gridy++;
            gc.weightx = 1;
            gc.weighty = 0.2;

            gc.gridx = 0;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.FIRST_LINE_END;
            gc.insets = new Insets(0,0,0,5);
            add(niLabel, gc);

            gc.gridx = 1;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            gc.insets = new Insets(0,0,0,0);
            add(niNumber, gc);

            //////////Next row///////////////////////////

            gc.gridy++;
            gc.weightx = 1;
            gc.weighty = 0.03;

            gc.gridx = 0;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_END;
            gc.insets = new Insets(0,0,0,5);
            add(new JLabel("Gender"), gc);

            gc.gridx = 1;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            gc.insets = new Insets(0,0,0,0);
            add(maleRadio, gc);

            //////////Next row///////////////////////////

            gc.gridy++;
            gc.weightx = 1;
            gc.weighty = 0.2;

            gc.gridx = 1;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            gc.insets = new Insets(0,0,0,0);
            add(femaleRadio, gc);

            //////////Next row///////////////////////////

            gc.gridy++;
            gc.weightx = 1;
            gc.weighty = 2.0;

            gc.gridx = 1;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            gc.insets = new Insets(0,0,0,0);
            add(okBtn, gc);




    }

    public void setFormListener(FormListener listener){
        this.formListener = listener;
    }
}

    class AgeCategory{
    private int id;
    private String text;

    public AgeCategory(int id, String text) {
    this.id = id;
    this.text = text;
    }

    public String toString() {
        return text;
    }

    public int getId() {
        return id;
    }

    }