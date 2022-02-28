package com.invoice.view;

import com.invoice.model.InvoiceHeader;
import com.invoice.model.InvoiceLine;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Frame extends JFrame implements ActionListener, ListSelectionListener {
    // Private Variables using in program
    private JMenuItem LoadFile;
    private JMenuItem SaveFile;
    private JMenu Menu;
    private JMenuBar MenuBar;
    private JLabel JL;
    private JTable Right_Table;
    private ArrayList<ArrayList<String>> Data = new ArrayList<>();
    private JLabel InvoiceNumber;
    private JTextField InvoiceData;
    private JTextField CustomerName;
    private JLabel InvoiceTotal;
    private JButton CancelButton;
    private JButton SaveButton;

    private JTable RightTable;
    private ArrayList<ArrayList<String>> DataNext = new ArrayList<>();
    private JButton CreateNewButton;
    private JButton DeleteButton;

    public Frame (String title){
        super(title);
        LoadFile = new JMenuItem("Load File"); LoadFile.setActionCommand("load"); LoadFile.addActionListener(this);
        SaveFile= new JMenuItem("Save File"); SaveFile.setActionCommand("save"); SaveFile.addActionListener(this);
        MenuBar = new JMenuBar();
        Menu = new JMenu("File");

        setJMenuBar(MenuBar);
        MenuBar.add(Menu);
        Menu.add(LoadFile);
        Menu.add(SaveFile);


        JPanel main = new JPanel();
        main.setLayout(new GridLayout(1,2));

        JPanel left = new JPanel();
        left.setLayout(new GridLayout(1,1));
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(1,2));

        JPanel ll= new JPanel();
        ll.setLayout(new BorderLayout(16,16));

        JPanel rr= new JPanel();
        rr.setLayout(new BorderLayout(16,16));

        JPanel r= new JPanel();
        r.setLayout(new BoxLayout(r,BoxLayout.Y_AXIS));

        JPanel r1= new JPanel();
        r1.setLayout(new FlowLayout());


        JPanel f=new JPanel();
        f.setLayout(new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        JL = new JLabel("     Invoice Table");
        ll.add(JL, BorderLayout.NORTH);

        ArrayList<String> row1= new ArrayList<>();
        row1.add(null);
        row1.add(null);
        row1.add(null);
        row1.add(null);
        ArrayList<String> row2= new ArrayList<>();
        row2.add(null);
        row2.add(null);
        row2.add(null);
        row2.add(null);
        ArrayList<String> row3= new ArrayList<>();
        row3.add(null);
        row3.add(null);
        row3.add(null);
        row3.add(null);

        Data.add(row1);
        Data.add(row2);
        Data.add(row3);

        MyTable TLeft = new MyTable(new String[]{"No.","Date","Customer","Total"}, Data);
        RightTable = new JTable(TLeft);
        RightTable = new JTable(TLeft);
        RightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        RightTable.getSelectionModel().addListSelectionListener(this);
        ll.add(new JScrollPane(RightTable), BorderLayout.CENTER);


        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER,26,11));

        CreateNewButton = new JButton("Create New Invoice"); CreateNewButton.setActionCommand("create"); CreateNewButton.addActionListener(this);
        bottom.add(CreateNewButton);
        DeleteButton = new JButton("Delete Invoice"); DeleteButton.setActionCommand("delete"); DeleteButton.addActionListener(this);
        bottom.add(DeleteButton);

        ll.add(bottom, BorderLayout.SOUTH);


        c.gridx=0;
        c.gridy=0;
        f.add(new JLabel("Invoice Number"), c);

        c.gridx=1;
        c.gridy=0;
        InvoiceNumber = new JLabel("");
        f.add(InvoiceNumber, c);

        c.gridx=0;
        c.gridy=1;
        f.add(new JLabel("Invoice Date   "), c);

        InvoiceData = new JTextField(20);
        c.gridx=1;
        c.gridy=1;
        f.add(InvoiceData, c);

        c.gridx=0;
        c.gridy=2;
        f.add(new JLabel("Customer Name   "), c);

        c.gridx=1;
        c.gridy=2;
        CustomerName= new JTextField(15);
        f.add(CustomerName, c);

        c.gridx=0;
        c.gridy=3;
        f.add(new JLabel("Invoice Total   "), c);

        c.gridx=1;
        c.gridy=3;
        InvoiceTotal =new JLabel("  ");
        f.add(InvoiceTotal, c);

        rr.add(f, BorderLayout.NORTH);

        r.add(new JLabel("Invoice Items  "));

        ArrayList<String> rrow1= new ArrayList<>();
        rrow1.add(null);
        rrow1.add(null);
        rrow1.add(null);
        rrow1.add(null);
        rrow1.add(null);
        ArrayList<String> rrow2= new ArrayList<>();
        rrow2.add(null);
        rrow2.add(null);
        rrow2.add(null);
        rrow2.add(null);
        rrow2.add(null);
        ArrayList<String> rrow3= new ArrayList<>();
        rrow3.add(null);
        rrow3.add(null);
        rrow3.add(null);
        rrow3.add(null);
        rrow3.add(null);

        DataNext.add(rrow1);
        DataNext.add(rrow2);
        DataNext.add(rrow3);

        MyTable TRight = new MyTable( new String[]{"No.","Item Name","Item Price","Count","Item Total"}, DataNext);
        Right_Table = new JTable(TRight);
        r.add(new JScrollPane(Right_Table));
        rr.add(r,BorderLayout.CENTER );

        JPanel bott = new JPanel();
        bott.setLayout(new FlowLayout(FlowLayout.CENTER,26,11));
        SaveButton = new JButton("Create"); SaveButton.setActionCommand("saveButt"); SaveButton.addActionListener(this);
        bott.add(SaveButton);
        CancelButton = new JButton("Cancel"); CancelButton.setActionCommand("cancel"); CancelButton.addActionListener(this);
        bott.add(CancelButton);
        rr.add(bott, BorderLayout.SOUTH);

        left.add(ll);
        right.add(rr);
        main.add(left);
        main.add(right);
        add(main);
        setSize(800,500);
        setLocation(250,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        LeftTableRowSelected();
    }


    private ArrayList<InvoiceHeader> Invoices = new ArrayList<>();
    private ArrayList<InvoiceLine> lines = new ArrayList<>();
    private LeftTable headerModel;
    private RightTable lineModel;
    private SimpleDateFormat dd= new SimpleDateFormat("dd-mm-yyyy");
    private LeftSidePanel headerDialogue;
    private RightSidePanel lineDialogue;
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand()=="load")
        {
            System.out.println("choose invoiceHeader file");
            try { loadFile();}
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Wrong File Format (Choose .csv file)","Error Message",JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); }
        }

        else if(e.getActionCommand()=="save") {
            try{saveFile();}
            catch (IOException nf){System.out.println(nf);}

        }
        else if(e.getActionCommand()=="create") {createInvoice();}
        else if(e.getActionCommand()=="delete") {
            try {deleteInvoice();}
            catch (Exception file){
                JOptionPane.showMessageDialog(this,"Please select an invoice to delete","Error Message",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getActionCommand()=="saveButt") {createItem();}
        else if(e.getActionCommand()=="cancel") {
            try {deleteItem();}
            catch (Exception item) {JOptionPane.showMessageDialog(this,"Please select an item to delete","Error Message",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getActionCommand()=="createOk") {createInvoiceOk ();}
        else if(e.getActionCommand()=="createCancel") {createInvoiceCancel ();}
        else if(e.getActionCommand()=="createItemOk") {createItemOk ();}
        else if(e.getActionCommand()=="CancelItem") {createItemCancel ();}
    }
    private void loadFile() throws Exception{
        Invoices.clear();
        JOptionPane.showMessageDialog(this,"Please select invoice header file ", "Invoice Header",JOptionPane.WARNING_MESSAGE);
        JFileChooser file= new JFileChooser();
        int option = file.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION)
        {
            File selected = file.getSelectedFile();
            FileReader fr= new FileReader(selected);
            BufferedReader buffer = new BufferedReader(fr);
            String inv= null;
            while ((inv = buffer.readLine()) != null) {
                String[] invoSegments = inv.split(",");
                String invNoStr = invoSegments[0];
                String invDateStr = invoSegments[1];
                String customerN = invoSegments[2];

                System.out.println(customerN);
                int invNo = Integer.parseInt(invNoStr);
                Date invDate = dd.parse(invDateStr);
                InvoiceHeader header = new InvoiceHeader(invNo, invDate,customerN);
                Invoices.add(header);
            }
            buffer.close();
            fr.close();
            System.out.println("choose invoice details file");

            JOptionPane.showMessageDialog(this,"Please select invoice header line ", "Invoice Line",JOptionPane.WARNING_MESSAGE);
            file= new JFileChooser();
            option = file.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION)
            {
                selected = file.getSelectedFile();
                fr= new FileReader(selected);
                buffer = new BufferedReader(fr);
                while ((inv = buffer.readLine()) != null) {
                    String[] invoLines = inv.split(",");
                    String invNoStr = invoLines[0];
                    String item = invoLines[1];
                    String priceStr = invoLines[2];
                    String countStr = invoLines[3];

                    int invNo = Integer.parseInt(invNoStr);
                    double price = Double.parseDouble(priceStr);
                    int count = Integer.parseInt(countStr);
                    InvoiceHeader header = findByNumber(invNo);
                    InvoiceLine invoiceLine = new InvoiceLine(item ,price, count, header);
                    header.addLines(invoiceLine);
                }
            }
            buffer.close();
            fr.close();
            headerModel = new LeftTable(Invoices);
            RightTable.setModel(headerModel);
            RightTable.validate();

        }
    }
    private void saveFile () throws IOException {
        JOptionPane.showMessageDialog(this, "Save invoice header file ", "Invoice Header", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser fs = new JFileChooser();
        fs.setDialogTitle("Choose File Save");
        int userSelection = fs.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fs.getSelectedFile();
            FileWriter fw = new FileWriter(fileToSave + ".csv");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < RightTable.getRowCount(); i++) {
                for (int j = 0; j < RightTable.getColumnCount(); j++) {

                    bw.write(RightTable.getValueAt(i, j).toString() + ",");
                }
                bw.newLine();
            }
            JOptionPane.showMessageDialog(this, "Saved Successfully ", "Save Message", JOptionPane.INFORMATION_MESSAGE);
            bw.close();
            fw.close();



            JOptionPane.showMessageDialog(this, "Save invoice line file ", "Invoice Line", JOptionPane.INFORMATION_MESSAGE);
            fs = new JFileChooser();
            fs.setDialogTitle("Choose File Save");
            userSelection = fs.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                fileToSave = fs.getSelectedFile();
                fw = new FileWriter(fileToSave + ".csv");
                bw = new BufferedWriter(fw);
                for (InvoiceHeader inv: Invoices) {
                    for (InvoiceLine item: inv.getLines()) {
                        bw.write(inv.getInvoice_Number()+ "," +item.getItem_Name()+ "," +item.getItem_Price()+ "," + item.getCount());
                        bw.newLine();
                    }

                }
                JOptionPane.showMessageDialog(this, "Saved Successfully ", "Save Message", JOptionPane.INFORMATION_MESSAGE);
                bw.close();
                fw.close();

            }

        }
    }

    private void createInvoice (){
        headerDialogue = new LeftSidePanel (this);
        headerDialogue.setLocationRelativeTo(null);
        headerDialogue.setTitle("Create New Invoice");
        headerDialogue.setVisible(true);
    }
    private void deleteInvoice () throws Exception {
        RightTable.setModel(headerModel);
        int x = RightTable.getSelectedRow();
        Invoices.remove(x);
        headerModel.fireTableDataChanged();
    }

    private void createItem () {
        lineDialogue = new RightSidePanel(this);
        lineDialogue.setLocationRelativeTo(null);
        lineDialogue.setTitle("Create New Invoice Item");
        lineDialogue.setVisible(true);

    }
    private void deleteItem () throws Exception{
        Right_Table.setModel(lineModel);
        int x = Right_Table.getSelectedRow();
        lines.remove(x);
        lineModel.fireTableDataChanged();
    }


    private InvoiceHeader findByNumber (int no)
    {
        for (InvoiceHeader header : Invoices) { if (header.getInvoice_Number() == no) {return header;}  }
        return null;
    }

    private void LeftTableRowSelected() {
        RightTable.setModel(headerModel);
        int selectedRow= RightTable.getSelectedRow();
        if (selectedRow != -1)
        {
            InvoiceHeader row = headerModel.getInvoices().get(selectedRow);
            InvoiceNumber.setText(""+row.getInvoice_Number());
            CustomerName.setText(row.getCustomer_Name());
            InvoiceData.setText(row.getInvoice_Date().toString());
            InvoiceTotal.setText(""+row.getInvoiceTotal());
            lines = row.getLines();
            lineModel = new RightTable(lines);
            Right_Table.setModel(lineModel);
            lineModel.fireTableDataChanged();
        }
    }

    private void createInvoiceOk() {

        String customer = headerDialogue.getCustomerName().getText();
        String invDateStr = headerDialogue.getInvoiceDate().getText();
        Date invDate = new Date();
        try{
            invDate = dd.parse(invDateStr);
        } catch(ParseException ex){
            JOptionPane.showMessageDialog(this,"Wrong Data Format","Error Message",JOptionPane.ERROR_MESSAGE);

        }

        headerDialogue.setVisible(false);
        int num = getLastNum()+1;
        InvoiceHeader newInv = new InvoiceHeader(num,invDate,customer);
        Invoices.add(newInv);
        headerModel.fireTableDataChanged();

    }
    private void createInvoiceCancel(){headerDialogue.setVisible(false); }
    private void createItemOk () {
        String itemName = lineDialogue.getItemName().getText();
        String itemCountStr = lineDialogue.getItemCount().getText();
        String itemPriceStr= lineDialogue.getItemPrice().getText();
        lineDialogue.setVisible(false);

        int itemCount = Integer.parseInt(itemCountStr);
        double itemPrice= Double.parseDouble(itemPriceStr);
        InvoiceHeader invoiceHeader = Invoices.get(RightTable.getSelectedRow());
        InvoiceLine line = new InvoiceLine(itemName,itemPrice,itemCount, invoiceHeader );
        invoiceHeader.addLines(line);
        lineModel.fireTableDataChanged();
        headerModel.fireTableDataChanged();


    }
    private void createItemCancel(){lineDialogue.setVisible(false);}
    private int getLastNum(){
        int num= 0;
        for (InvoiceHeader header : Invoices) {
            if (header.getInvoice_Number() > num)
            {
                num = header.getInvoice_Number();
            }

        }
        return num;
    }




}
