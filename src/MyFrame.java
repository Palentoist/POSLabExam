import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class MyFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfcode;
    private JTextField tfname;
    private JTextField tfquantity;
    private JTextField tfunprice;
    private JLabel lbltotalshow;
    private JLabel lblgrandtotal;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyFrame frame = new MyFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MyFrame() {
        setTitle("POS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 750);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblcategory = new JLabel("Select Category : ");
        lblcategory.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblcategory.setBounds(43, 23, 151, 34);
        contentPane.add(lblcategory);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem("Apple");
        comboBox.addItem("Orange");
        comboBox.addItem("Banana");
        comboBox.addItem("Mango");
        comboBox.setBounds(220, 31, 238, 22);
        contentPane.add(comboBox);

        JLabel lblcode = new JLabel("Item Code :");
        lblcode.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblcode.setBounds(43, 68, 151, 34);
        contentPane.add(lblcode);

        tfcode = new JTextField();
        tfcode.setBounds(220, 75, 238, 22);
        contentPane.add(tfcode);
        tfcode.setColumns(10);

        JLabel lblname = new JLabel("Item Name :");
        lblname.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblname.setBounds(43, 113, 151, 34);
        contentPane.add(lblname);

        tfname = new JTextField();
        tfname.setColumns(10);
        tfname.setBounds(220, 122, 238, 22);
        contentPane.add(tfname);

        JLabel lblquantity = new JLabel("Quantity :");
        lblquantity.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblquantity.setBounds(43, 158, 151, 34);
        contentPane.add(lblquantity);

        tfquantity = new JTextField();
        tfquantity.setColumns(10);
        tfquantity.setBounds(220, 167, 238, 22);
        contentPane.add(tfquantity);

        JLabel lblunprice = new JLabel("Unit Price :");
        lblunprice.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblunprice.setBounds(43, 203, 151, 34);
        contentPane.add(lblunprice);

        tfunprice = new JTextField();
        tfunprice.setColumns(10);
        tfunprice.setBounds(220, 212, 238, 22);
        contentPane.add(tfunprice);

        JLabel lbltotal = new JLabel("Total :");
        lbltotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbltotal.setBounds(43, 248, 151, 34);
        contentPane.add(lbltotal);

        lbltotalshow = new JLabel("");
        lbltotalshow.setHorizontalAlignment(SwingConstants.TRAILING);
        lbltotalshow.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbltotalshow.setBounds(197, 248, 151, 34);
        contentPane.add(lbltotalshow);

        JButton btnadd = new JButton("Add");
        btnadd.setBounds(53, 293, 89, 23);
        contentPane.add(btnadd);

        JButton btnupdate = new JButton("Update");
        btnupdate.setBounds(234, 293, 89, 23);
        contentPane.add(btnupdate);

        JButton btndelete = new JButton("Delete");
        btndelete.setBounds(454, 293, 89, 23);
        contentPane.add(btndelete);

        String[] columns = {"Category", "Item Code", "Item Name", "Quantity", "Unit Price", "Total"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(43, 330, 500, 200);
        contentPane.add(scrollPane);

        JLabel lblGrandTotalLabel = new JLabel("Full Total:");
        lblGrandTotalLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblGrandTotalLabel.setBounds(43, 550, 151, 34);
        contentPane.add(lblGrandTotalLabel);

        lblgrandtotal = new JLabel("0.00");
        lblgrandtotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblgrandtotal.setBounds(172, 550, 151, 34);
        contentPane.add(lblgrandtotal);

        tfquantity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });

        tfunprice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });

        btnadd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItemToTable(comboBox.getSelectedItem().toString());
                tfname.setText("");
                tfcode.setText("");
                tfquantity.setText("");
                tfunprice.setText("");
            }
        });

        btnupdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateSelectedRow();
                tfname.setText("");
                tfcode.setText("");
                tfquantity.setText("");
                tfunprice.setText("");
            }
        });

        btndelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRow();
                calculateGrandTotal();
                
            }
        });
    }

    private void calculateTotal() {
        try {
            double quantity = Double.parseDouble(tfquantity.getText());
            double unitPrice = Double.parseDouble(tfunprice.getText());
            double total = quantity * unitPrice;
            lbltotalshow.setText(String.format("%.2f", total));
        } catch (NumberFormatException e) {
            lbltotalshow.setText("");
        }
    }

    private void addItemToTable(String category) {
        try {
            String itemCode = tfcode.getText();
            String itemName = tfname.getText();
            double quantity = Double.parseDouble(tfquantity.getText());
            double unitPrice = Double.parseDouble(tfunprice.getText());
            double total = quantity * unitPrice;

            tableModel.addRow(new Object[]{category, itemCode, itemName, quantity, unitPrice, total});

            calculateGrandTotal();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for quantity and unit price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                String category = (String) tableModel.getValueAt(selectedRow, 0);
                String itemCode = tfcode.getText();
                String itemName = tfname.getText();
                double quantity = Double.parseDouble(tfquantity.getText());
                double unitPrice = Double.parseDouble(tfunprice.getText());
                double total = quantity * unitPrice;

                tableModel.setValueAt(category, selectedRow, 0);
                tableModel.setValueAt(itemCode, selectedRow, 1);
                tableModel.setValueAt(itemName, selectedRow, 2);
                tableModel.setValueAt(quantity, selectedRow, 3);
                tableModel.setValueAt(unitPrice, selectedRow, 4);
                tableModel.setValueAt(total, selectedRow, 5);

                calculateGrandTotal();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for quantity and unit price.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateGrandTotal() {
        double grandTotal = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            double rowTotal = (Double) tableModel.getValueAt(i, 5);
            grandTotal += rowTotal;
        }
        lblgrandtotal.setText(String.format("%.2f", grandTotal));
    }
}