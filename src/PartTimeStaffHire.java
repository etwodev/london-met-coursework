public class PartTimeStaffHire extends StaffHire {
  private int workingHour;
  private double wagesPerHour;
  private String shifts;
  private boolean terminated;

  public PartTimeStaffHire(int vacancyNumber, String designation, String jobType, String staffName,
      String joiningDate, String qualification, String appointedBy, boolean joined,
      int workingHour, double wagesPerHour, String shifts) {
    super(vacancyNumber, designation, jobType, staffName, joiningDate, qualification, appointedBy, joined);
    this.workingHour = workingHour;
    this.wagesPerHour = wagesPerHour;
    this.shifts = shifts;
    this.terminated = false;
  }

  public int getWorkingHour() {
    return workingHour;
  }

  public double getWagesPerHour() {
    return wagesPerHour;
  }

  public String getShifts() {
    return shifts;
  }

  public boolean isTerminated() {
    return terminated;
  }

  public void setShifts(String newShift) {
    if (isJoined()) {
      this.shifts = newShift;
    } else {
      System.out.println("Staff not joined; cannot set shift.");
    }
  }

  public void terminateStaff() {
    if (terminated) {
      System.out.println("Staff already terminated.");
    } else {
      setStaffName("");
      setJoiningDate("");
      setQualification("");
      setAppointedBy("");
      setJoined(false);
      this.terminated = true;
      System.out.println("Staff successfully terminated.");
    }
  }

  @Override
  public void display() {
    super.display();
    System.out.println("Working Hour: " + workingHour);
    System.out.println("Wages Per Hour: " + wagesPerHour);
    System.out.println("Shifts: " + shifts);
    System.out.println("Terminated: " + terminated);
    System.out.println("Income Per Day: " + (wagesPerHour * workingHour));
  }
}
