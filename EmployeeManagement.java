package employeemanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagement {

    public static void main(String[] args) {
        EmployeeManagementPage employeeManagementPage = new EmployeeManagementPage();
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        UpdateEmployeePage updateEmployeePage = new UpdateEmployeePage();
        RemoveEmployeePage removeEmployeePage = new RemoveEmployeePage();
        ViewEmployeesPage viewEmployeesPage = new ViewEmployeesPage();

        employeeManagementPage.setAddEmployeeAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployeePage.setVisible(true);
            }
        });

        employeeManagementPage.setUpdateEmployeeAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployeePage.setVisible(true);
            }
        });

        employeeManagementPage.setRemoveEmployeeAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEmployeePage.setVisible(true);
            }
        });

        employeeManagementPage.setViewEmployeesAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewEmployeesPage.displayEmployees();
                viewEmployeesPage.setVisible(true);
            }
        });

        addEmployeePage.setAddAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = addEmployeePage.getName();
                int id = addEmployeePage.getID();
                String role = addEmployeePage.getRole();
                EmployeeDAO.addEmployee(name, id, role);
                addEmployeePage.setVisible(false);
            }
        });

        updateEmployeePage.setUpdateAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = updateEmployeePage.getID();
                String newRole = updateEmployeePage.getNewRole();
                EmployeeDAO.updateEmployeeRole(id, newRole);
                updateEmployeePage.setVisible(false);
            }
        });

        removeEmployeePage.setRemoveAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = removeEmployeePage.getID();
                EmployeeDAO.removeEmployee(id);
                removeEmployeePage.setVisible(false);
            }
        });
    }
}

class EmployeeManagementPage extends JFrame {

    private JButton addEmployeeButton;
    private JButton updateEmployeeButton;
    private JButton removeEmployeeButton;
    private JButton viewEmployeesButton;

    public EmployeeManagementPage() {
        setTitle("Employee Management");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addEmployeeButton = new JButton("Add Employee");
        updateEmployeeButton = new JButton("Update Employee");
        removeEmployeeButton = new JButton("Remove Employee");
        viewEmployeesButton = new JButton("View Employees");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(addEmployeeButton);
        panel.add(updateEmployeeButton);
        panel.add(removeEmployeeButton);
        panel.add(viewEmployeesButton);

        getContentPane().add(panel);

        setVisible(true);
    }

    public void setAddEmployeeAction(ActionListener listener) {
        addEmployeeButton.addActionListener(listener);
    }

    public void setUpdateEmployeeAction(ActionListener listener) {
        updateEmployeeButton.addActionListener(listener);
    }

    public void setRemoveEmployeeAction(ActionListener listener) {
        removeEmployeeButton.addActionListener(listener);
    }

    public void setViewEmployeesAction(ActionListener listener) {
        viewEmployeesButton.addActionListener(listener);
    }
}

class AddEmployeePage extends JFrame {

    private JTextField nameField;
    private JTextField idField;
    private JTextField roleField;
    private JButton addButton;

    public AddEmployeePage() {
        setTitle("Add Employee");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Add Employee");
        JLabel nameLabel = new JLabel("Name:");
        JLabel idLabel = new JLabel("ID:");
        JLabel roleLabel = new JLabel("Role:");

        nameField = new JTextField(20);
        idField = new JTextField(20);
        roleField = new JTextField(20);
        addButton = new JButton("Add");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(roleLabel);
        panel.add(roleField);
        panel.add(new JLabel());
        panel.add(addButton);

        getContentPane().add(BorderLayout.CENTER, panel);
        getContentPane().add(BorderLayout.NORTH, titleLabel);

        setVisible(false);
    }

    public String getName() {
        return nameField.getText();
    }

    public int getID() {
        return Integer.parseInt(idField.getText());
    }

    public String getRole() {
        return roleField.getText();
    }

    public void setAddAction(ActionListener listener) {
        addButton.addActionListener(listener);
    }
}

class UpdateEmployeePage extends JFrame {

    private JTextField idField;
    private JTextField newRoleField;
    private JButton updateButton;

    public UpdateEmployeePage() {
        setTitle("Update Employee Role");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Update Employee Role");
        JLabel idLabel = new JLabel("ID:");
        JLabel newRoleLabel = new JLabel("New Role:");

        idField = new JTextField(20);
        newRoleField = new JTextField(20);
        updateButton = new JButton("Update");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(idLabel);
        panel.add(idField);
        panel.add(newRoleLabel);
        panel.add(newRoleField);
        panel.add(new JLabel());
        panel.add(updateButton);

        getContentPane().add(BorderLayout.CENTER, panel);
        getContentPane().add(BorderLayout.NORTH, titleLabel);

        setVisible(false);
    }

    public int getID() {
        return Integer.parseInt(idField.getText());
    }

    public String getNewRole() {
        return newRoleField.getText();
    }

    public void setUpdateAction(ActionListener listener) {
        updateButton.addActionListener(listener);
    }
}

class RemoveEmployeePage extends JFrame {

    private JTextField idField;
    private JButton removeButton;

    public RemoveEmployeePage() {
        setTitle("Remove Employee");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Remove Employee");
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(20);
        removeButton = new JButton("Remove");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(idLabel);
        panel.add(idField);
        panel.add(new JLabel());
        panel.add(removeButton);

        getContentPane().add(BorderLayout.CENTER, panel);
        getContentPane().add(BorderLayout.NORTH, titleLabel);

        setVisible(false);
    }

    public int getID() {
        return Integer.parseInt(idField.getText());
    }

    public void setRemoveAction(ActionListener listener) {
        removeButton.addActionListener(listener);
    }
}

class ViewEmployeesPage extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;

    public ViewEmployeesPage() {
        setTitle("View Employees");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table = new JTable();
        scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(panel);

        setVisible(false);
    }

    public void displayEmployees() {
        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

            List<String[]> data = new ArrayList<>();
            while (resultSet.next()) {
                String[] row = new String[3];
                row[0] = resultSet.getString("name");
                row[1] = String.valueOf(resultSet.getInt("id"));
                row[2] = resultSet.getString("role");
                data.add(row);
            }

            String[] columnNames = { "Name", "ID", "Role" };
            String[][] dataArray = new String[data.size()][3];
            for (int i = 0; i < data.size(); i++) {
                dataArray[i] = data.get(i);
            }

            table.setModel(new DefaultTableModel(dataArray, columnNames));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/employeedb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

class EmployeeDAO {
    public static void addEmployee(String name, int id, String role) {
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO employees (name, id, role) VALUES (?, ?, ?)");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.setString(3, role);
            statement.executeUpdate();
            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
            // Rollback transaction if an error occurs
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DatabaseManager.closeConnection(connection);
        }
    }

    public static void updateEmployeeRole(int id, String newRole) {
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE employees SET role = ? WHERE id = ?");
            statement.setString(1, newRole);
            statement.setInt(2, id);
            statement.executeUpdate();
            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
            // Rollback transaction if an error occurs
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DatabaseManager.closeConnection(connection);
        }
    }

    public static void removeEmployee(int id) {
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
            // Rollback transaction if an error occurs
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DatabaseManager.closeConnection(connection);
        }
    }
}
