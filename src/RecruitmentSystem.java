import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecruitmentSystem {
  private JFrame frame;
  private ArrayList<StaffHire> staffList = new ArrayList<>();

  private JTextField tfVacancyNumber, tfDesignation, tfJobType, tfStaffName,
      tfJoiningDate, tfQualification, tfAppointedBy, tfSalary,
      tfWeeklyHours, tfWorkingHour, tfWagesPerHour, tfShifts, tfDisplayIndex;

  private JButton btnAddFullTime, btnAddPartTime, btnSetSalary,
      btnSetShift, btnTerminate, btnDisplay, btnClear;

  public RecruitmentSystem() {
    frame = new JFrame("Recruitment System");
    frame.setLayout(new GridLayout(20, 2, 5, 5));

    tfVacancyNumber = new JTextField();
    tfDesignation = new JTextField();
    tfJobType = new JTextField();
    tfStaffName = new JTextField();
    tfJoiningDate = new JTextField();
    tfQualification = new JTextField();
    tfAppointedBy = new JTextField();
    tfSalary = new JTextField();
    tfWeeklyHours = new JTextField();
    tfWorkingHour = new JTextField();
    tfWagesPerHour = new JTextField();
    tfShifts = new JTextField();
    tfDisplayIndex = new JTextField();

    addLabelAndField("Vacancy Number", tfVacancyNumber);
    addLabelAndField("Designation", tfDesignation);
    addLabelAndField("Job Type", tfJobType);
    addLabelAndField("Staff Name", tfStaffName);
    addLabelAndField("Joining Date", tfJoiningDate);
    addLabelAndField("Qualification", tfQualification);
    addLabelAndField("Appointed By", tfAppointedBy);
    addLabelAndField("Salary", tfSalary);
    addLabelAndField("Weekly Fractional Hours", tfWeeklyHours);
    addLabelAndField("Working Hour", tfWorkingHour);
    addLabelAndField("Wages Per Hour", tfWagesPerHour);
    addLabelAndField("Shifts", tfShifts);
    addLabelAndField("Display Index", tfDisplayIndex);

    // Buttons
    btnAddFullTime = new JButton("Add Full Time Staff");
    btnAddPartTime = new JButton("Add Part Time Staff");
    btnSetSalary = new JButton("Set Salary");
    btnSetShift = new JButton("Set Working Shifts");
    btnTerminate = new JButton("Terminate Part Time Staff");
    btnDisplay = new JButton("Display");
    btnClear = new JButton("Clear");

    frame.add(btnAddFullTime);
    frame.add(btnAddPartTime);
    frame.add(btnSetSalary);
    frame.add(btnSetShift);
    frame.add(btnTerminate);
    frame.add(btnDisplay);
    frame.add(btnClear);

    btnAddFullTime.addActionListener(e -> addFullTimeStaff());
    btnAddPartTime.addActionListener(e -> addPartTimeStaff());
    btnSetSalary.addActionListener(e -> setSalary());
    btnSetShift.addActionListener(e -> setShifts());
    btnTerminate.addActionListener(e -> terminatePartTimeStaff());
    btnDisplay.addActionListener(e -> displayStaff());
    btnClear.addActionListener(e -> clearFields());

    frame.setSize(600, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  private void addLabelAndField(String labelText, JTextField field) {
    frame.add(new JLabel(labelText));
    frame.add(field);
  }

  private int getVacancyNumber() {
    try {
      return Integer.parseInt(tfVacancyNumber.getText().trim());
    } catch (NumberFormatException e) {
      showMessage("Invalid Vacancy Number");
      return -1;
    }
  }

  private void addFullTimeStaff() {
    int vacancy = getVacancyNumber();
    if (vacancy == -1)
      return;

    try {
      FullTimeStaffHire fullTime = new FullTimeStaffHire(
          vacancy,
          tfDesignation.getText(),
          tfJobType.getText(),
          tfStaffName.getText(),
          tfJoiningDate.getText(),
          tfQualification.getText(),
          tfAppointedBy.getText(),
          true,
          Double.parseDouble(tfSalary.getText()),
          Integer.parseInt(tfWeeklyHours.getText()));
      staffList.add(fullTime);
      showMessage("Full time staff added.");
    } catch (Exception e) {
      showMessage("Error adding full-time staff: " + e.getMessage());
    }
  }

  private void addPartTimeStaff() {
    int vacancy = getVacancyNumber();
    if (vacancy == -1)
      return;

    try {
      PartTimeStaffHire partTime = new PartTimeStaffHire(
          vacancy,
          tfDesignation.getText(),
          tfJobType.getText(),
          tfStaffName.getText(),
          tfJoiningDate.getText(),
          tfQualification.getText(),
          tfAppointedBy.getText(),
          true,
          Integer.parseInt(tfWorkingHour.getText()),
          Double.parseDouble(tfWagesPerHour.getText()),
          tfShifts.getText());
      staffList.add(partTime);
      showMessage("Part time staff added.");
    } catch (Exception e) {
      showMessage("Error adding part-time staff: " + e.getMessage());
    }
  }

  private void setSalary() {
    int vacancy = getVacancyNumber();

    for (StaffHire s : staffList) {
      if (s.getVacancyNumber() == vacancy && s instanceof FullTimeStaffHire) {
        FullTimeStaffHire fts = (FullTimeStaffHire) s;
        fts.setSalary(Double.parseDouble(tfSalary.getText()));
        showMessage("Salary updated.");
        return;
      }
    }
    showMessage("Full-time staff not found.");
  }

  private void setShifts() {
    int vacancy = getVacancyNumber();
    for (StaffHire s : staffList) {
      if (s.getVacancyNumber() == vacancy && s instanceof PartTimeStaffHire) {
        ((PartTimeStaffHire) s).setShifts(tfShifts.getText());
        showMessage("Shift updated.");
        return;
      }
    }
    showMessage("Part-time staff not found.");
  }

  private void terminatePartTimeStaff() {
    int vacancy = getVacancyNumber();
    for (StaffHire s : staffList) {
      if (s.getVacancyNumber() == vacancy && s instanceof PartTimeStaffHire) {
        ((PartTimeStaffHire) s).terminateStaff();
        showMessage("Part-time staff terminated.");
        return;
      }
    }
    showMessage("Part-time staff not found.");
  }

  private void displayStaff() {
    try {
      int index = Integer.parseInt(tfDisplayIndex.getText());
      if (index < 0 || index >= staffList.size()) {
        showMessage("Index out of bounds.");
        return;
      }
      staffList.get(index).display();
    } catch (NumberFormatException e) {
      showMessage("Invalid display index.");
    }
  }

  private void clearFields() {
    for (Component c : frame.getContentPane().getComponents()) {
      if (c instanceof JTextField) {
        ((JTextField) c).setText("");
      }
    }
  }

  private void showMessage(String msg) {
    JOptionPane.showMessageDialog(frame, msg);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(RecruitmentSystem::new);
  }
}
