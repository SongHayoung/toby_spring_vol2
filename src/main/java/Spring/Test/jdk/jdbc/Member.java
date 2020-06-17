package Spring.Test.jdk.jdbc;

public class Member {
    int id;
    String name;
    double point;

    public Member(int id, String name, double point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public double getPoint() { return this.point; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPoint(double point) { this.point = point; }
}
