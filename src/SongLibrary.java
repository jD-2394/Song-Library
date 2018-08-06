import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;




public class SongLibrary extends JFrame{

   public SongLibrary(){
       super("SongLibrary");
        JFrame mainWin = new JFrame();
        setLayout(new BorderLayout());
//        ArrayList<String> store = new ArrayList<String>();
        DefaultTableModel tab = new DefaultTableModel();
        String[] headers = {"Song", "Artist","Album", "Year"};
//        String[][] data = {
//                {"Soup", "Blind Melon", "Nico", "1998"},
//                {"Touch of Grey", "Grateful Dead","Live at Golden Gate Park","1991" },
//                {"U.S Blues","Grateful Dead","Live at Radio City Music Hall", "1980"}
//        };
        
        
        Box buttons = Box.createVerticalBox();;
        add(buttons,BorderLayout.EAST);
        
        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        //Box buttonBox = new Box(defaultCloseOperation);
        
        
        buttons.add(add, BorderLayout.NORTH);
        buttons.add(delete, BorderLayout.CENTER);
        JMenuBar main = new JMenuBar();
        setJMenuBar(main);
        JMenu SongLibrary = new JMenu("SongLibrary");
        JMenuItem about = new JMenuItem("About");
        JMenuItem exit = new JMenuItem("Exit");
        SongLibrary.add(about);
        SongLibrary.add(exit);
        main.add(SongLibrary);
        JMenu sTable = new JMenu("Table");
        JMenuItem _new = new JMenuItem ("New");
        JMenuItem open = new JMenuItem ("Open");
        JMenuItem save = new JMenuItem("Save");
        sTable.add(_new);
        sTable.add(open);
        sTable.add(save);
        main.add(sTable);
        
        tab.addColumn("Song");// names the column
        tab.addColumn("Artist");
        tab.addColumn("Album");
        tab.addColumn("Year");
        
        //model.addRow(rowData);
        
        JTable table = new JTable(tab);     
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        
        JScrollPane scroll = new JScrollPane(table);
        add(scroll);
        pack();
        
        add.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
//               String[] tIn = new String[4];
//                
//                Scanner in = new Scanner(System.in);
//                //in.useDelimiter(",\\s");
//                for(int i = 0; i < tIn.length; i++){
//                    tIn[i] = in.nextLine();
//                }
//               in.close();
               
                tab.addRow(new Object[]{});
               
            }
            
        });
        
        delete .addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               int check = table.getSelectedRow();
                
                if(check == -1){
                    JOptionPane.showMessageDialog(SongLibrary.this,"No row was selected","Message",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    tab.removeRow(check);
                }
                }
            
            
        });
        
        about.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SongLibrary.this,new  JLabel("<html><hr><i>Song Library</i><br>by Joseph Doye<hr></html>"),"Message",JOptionPane.INFORMATION_MESSAGE);
                }
            
            
        });
        
        exit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int close = JOptionPane.showConfirmDialog(SongLibrary.this, "Do you want to exit?");
                if(close == JOptionPane.YES_OPTION){
                    dispose();
             }
                }
            
            
        });
        _new.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               int result = JOptionPane.showConfirmDialog(SongLibrary.this, "Do you wish to clear the table?");
                if(result == JOptionPane.YES_OPTION){
               tab.setRowCount(0);
                }
            
            } 
        });

open.addActionListener(new ActionListener() {
    
    @Override
    public void actionPerformed(ActionEvent e) {
       ArrayList<Object> col = new ArrayList<Object>();
//        ArrayList<Object> row = new ArrayList<Object>();
//       JFileChooser open = new JFileChooser();
//        int result = open.showOpenDialog(SongLibrary.this);
//        if(result == JFileChooser.APPROVE_OPTION){
            //File file = open.getSelectedFile();
            int b = 0;
            
           try {
               File input = new File("file1.txt");
             String filepath =  input.getAbsolutePath();
            SongLibrary.this.setTitle("SongLibrary" + "[" + filepath + "]");
             System.out.println(filepath);
            Scanner in = new Scanner(input);
            in.useDelimiter(",");
            while(in.hasNextLine()){
                
                String temp = in.nextLine();
                
                String[] ar = temp.split(",");
                
                
                int a = 0;
                
               
                tab.addRow(new Object[]{ar[0],ar[1],ar[2],ar[3]});
            }
        }
        catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
          
           //while(in.has)
           
           
       // }
        
        
        }
    
    
});
save.addActionListener(new ActionListener() {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            File output = new File("output1.txt");
            PrintWriter out = new PrintWriter(output);
            for(int i = 0; i < tab.getRowCount(); i++){
               Object song = tab.getValueAt(i, 0);
               Object artist = tab.getValueAt(i, 1);
               Object album = tab.getValueAt(i, 2);
               Object year = tab.getValueAt(i, 3);
               out.println(song + "," + artist + "," + album + "," + year);
               System.out.println(song + "," + artist + "," + album + "," + year);

         }
            out.close();
     }
     catch (FileNotFoundException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
     }
                
                
                
                
                
        }
    
    
});
        
        
        
        
        
        
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new SongLibrary();
        frame.setVisible( true );
        }
}
