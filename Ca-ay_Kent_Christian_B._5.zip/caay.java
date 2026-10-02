import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CampusEventRegistration extends JFrame {
    JTextField nameField, ageField, collegeField;
    JComboBox<String> eventBox;
    JRadioButton participantRadio;
    JRadioButton audienceRadio;
    JRadioButton facultyRadio;
    ButtonGroup attendanceGroup;
    JCheckBox javaCheck;
    JCheckBox webCheck;
    JCheckBox cyberCheck;
    JCheckBox aiCheck;
    JCheckBox databaseCheck;
    JTextArea notesArea;
    JButton registerButton;
    JButton clearButton;
    public CampusEventRegistration() {
        setTitle("Campus Event Registration System");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
       
        panel.add(new JLabel("Participant Name:"));
        nameField = new JTextField();
        panel.add(nameField);
        
        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);
       
        panel.add(new JLabel("College/Department:"));
        collegeField = new JTextField();
        panel.add(collegeField);
        
        panel.add(new JLabel("Event Category:"));
        String[] events = {
            "Select Event",
            "Programming Competition",
            "Web Design Challenge",
            "Cybersecurity Seminar",
            "AI and Emerging Technologies",
            "IT Quiz Bowl"
        };
        eventBox = new JComboBox<>(events);
        panel.add(eventBox);
        
        panel.add(new JLabel("Attendance Type:"));
        JPanel attendancePanel = new JPanel(
            new GridLayout(3, 1)
        );
        participantRadio =
            new JRadioButton("Student Participant");
        audienceRadio =
            new JRadioButton("Student Audience");
        facultyRadio =
            new JRadioButton("Faculty/Staff");
        attendanceGroup = new ButtonGroup();
        attendanceGroup.add(participantRadio);
        attendanceGroup.add(audienceRadio);
        attendanceGroup.add(facultyRadio);
        attendancePanel.add(participantRadio);
        attendancePanel.add(audienceRadio);
        attendancePanel.add(facultyRadio);
        panel.add(attendancePanel);
        
        panel.add(new JLabel("Workshops to Attend:"));
        JPanel workshopPanel = new JPanel(
            new GridLayout(5, 1)
        );
        javaCheck =
            new JCheckBox("Java Programming");
        webCheck =
            new JCheckBox("Web Development");
        cyberCheck =
            new JCheckBox("Cybersecurity");
        aiCheck =
            new JCheckBox("Artificial Intelligence");
        databaseCheck =
            new JCheckBox("Database Management");
        workshopPanel.add(javaCheck);
        workshopPanel.add(webCheck);
        workshopPanel.add(cyberCheck);
        workshopPanel.add(aiCheck);
        workshopPanel.add(databaseCheck);
        panel.add(workshopPanel);
        
        panel.add(new JLabel("Special Request or Notes:"));
        notesArea = new JTextArea(5, 20);
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(notesArea));
       
        registerButton = new JButton("REGISTER");
        clearButton = new JButton("CLEAR");
        panel.add(registerButton);
        panel.add(clearButton);
        add(panel);
        
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
    }
    
    public void register() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String college = collegeField.getText().trim();
        
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter the participant name.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        if (ageText.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter the age.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "Age must be a whole number.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        if (age <= 0) {
            JOptionPane.showMessageDialog(
                this,
                "Age must be greater than zero.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        if (college.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter your college or department.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        if (eventBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                this,
                "Please select an event.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        if (!participantRadio.isSelected()
                && !audienceRadio.isSelected()
                && !facultyRadio.isSelected()) {
            JOptionPane.showMessageDialog(
                this,
                "Please select an attendance type.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        String event =
            eventBox.getSelectedItem().toString();
        String attendance = "";
        if (participantRadio.isSelected()) {
            attendance = "Student Participant";
        } else if (audienceRadio.isSelected()) {
            attendance = "Student Audience";
        } else {
            attendance = "Faculty/Staff";
        }
        
        String workshops = "";
        if (javaCheck.isSelected()) {
            workshops += "Java Programming\n";
        }
        if (webCheck.isSelected()) {
            workshops += "Web Development\n";
        }
        if (cyberCheck.isSelected()) {
            workshops += "Cybersecurity\n";
        }
        if (aiCheck.isSelected()) {
            workshops += "Artificial Intelligence\n";
        }
        if (databaseCheck.isSelected()) {
            workshops += "Database Management\n";
        }
        if (workshops.isEmpty()) {
            workshops = "None";
        }
        String notes = notesArea.getText().trim();
        if (notes.isEmpty()) {
            notes = "None";
        }
        
        String summary =
            "REGISTRATION SUCCESSFUL!\n\n"
            + "Participant Name: " + name + "\n"
            + "Age: " + age + "\n"
            + "College/Department: " + college + "\n"
            + "Event Category: " + event + "\n"
            + "Attendance Type: " + attendance + "\n\n"
            + "Workshops:\n" + workshops
            + "\nSpecial Request / Notes:\n"
            + notes;
        JOptionPane.showMessageDialog(
            this,
            summary,
            "Registration Summary",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
  
    public void clearForm() {
        nameField.setText("");
        ageField.setText("");
        collegeField.setText("");
        eventBox.setSelectedIndex(0);
        attendanceGroup.clearSelection();
        javaCheck.setSelected(false);
        webCheck.setSelected(false);
        cyberCheck.setSelected(false);
        aiCheck.setSelected(false);
        databaseCheck.setSelected(false);
        notesArea.setText("");
    }
    public static void main(String[] args) {
        new CampusEventRegistration().setVisible(true);
    }
}
