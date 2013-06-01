package cae.rev;

/*
 * TableFilterDemo.java requires SpringUtilities.java
 */

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import cae.ProgramInfo;
import cae.Services;
import cae.SpringUtilities;

@SuppressWarnings("serial")
public class FastSoft extends JPanel {
    
	private static JFrame frame;
	private JTable table;
	private ProgramTableModel model;
    private JTextField filterText;
    private TableRowSorter<ProgramTableModel> sorter;
   
    private JButton edit = null;
    private JButton openFolder = null;
    private JButton openExe = null;
    private JButton openKeygen = null;
    
    
    private static ProgramInfo info = null;

    public FastSoft() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Create a table with a sorter.
        List<ProgramInfo> data = new Services().getPrograms(new File("H:/SOFT"));
        model = new ProgramTableModel(data);
        sorter = new TableRowSorter<ProgramTableModel>(model);
        table = new JTable(model);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(800, 200));
        table.setFillsViewportHeight(true);

        //For the purposes of this example, better to have a single
        //selection.
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //When selection changes, provide user with row numbers for
        //both view and model.
        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        int viewRow = table.getSelectedRow();
                        if (viewRow < 0) {
                            //Selection got filtered away.
                            openFolder.setEnabled(false);
                            openExe.setEnabled(false);
                            openKeygen.setEnabled(false);
                            edit.setEnabled(false);
                        } else {
                            int modelRow = 
                                table.convertRowIndexToModel(viewRow);
                            FastSoft.setInfo(model.getRowObject(modelRow));
                            edit.setEnabled(true);
                            openFolder.setEnabled(true);
                            openExe.setEnabled((FastSoft.info.getExe() != null));
                            openKeygen.setEnabled((FastSoft.info.getKeygen() != null));
                        }
                    }
                }
        );


        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);

        //Create a separate form for filterText and statusText
        JPanel form = new JPanel(new SpringLayout());
        JLabel l1 = new JLabel("Filter Text:", SwingConstants.TRAILING);
        form.add(l1);
        
        
        filterText = new JTextField();
        //Whenever filterText changes, invoke newFilter.
        filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        l1.setLabelFor(filterText);
        form.add(filterText);
        
        JLabel l2 = new JLabel("Actions:", SwingConstants.TRAILING);
        form.add(l2);
        edit = new JButton("Edit Info");
        edit.setEnabled(false);
        edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				editInfo(info);
			}
        	
        });
        l2.setLabelFor(edit);
        form.add(edit);
        
        JLabel l3 = new JLabel("        ", SwingConstants.TRAILING);
        form.add(l3);
        openFolder = new JButton("Open Folder");
        openFolder.setEnabled(false);
        openFolder.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				open(info);
			}
        	
        });
        l3.setLabelFor(openFolder);
        form.add(openFolder);
        
        JLabel l4 = new JLabel("        ", SwingConstants.TRAILING);
        form.add(l4);
        openExe = new JButton("Open Exe");
        openExe.setEnabled(false);
        openExe.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				openExe(info.getExe().getAbsolutePath());
			}
        	
        });
        l4.setLabelFor(openExe);
        form.add(openExe);
        
        JLabel l5 = new JLabel("        ", SwingConstants.TRAILING);
        form.add(l5);
        openKeygen = new JButton("Open Keygen");
        openKeygen.setEnabled(false);
        openKeygen.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				openExe(info.getKeygen().getAbsolutePath());
			}
        	
        });
        l5.setLabelFor(openKeygen);
        form.add(openKeygen);
        
        SpringUtilities.makeCompactGrid(form, 5, 2, 6, 6, 6, 6);
        add(form);
    }

    protected void editInfo(ProgramInfo p) {
        JDialog frame = new JDialog(FastSoft.frame);//"Interactive Form");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	model.fireTableDataChanged();
            }
        });
        frame.getContentPane().add(new ProgramInfoEditor(p));
        frame.pack();
        frame.setVisible(true);
	}

	protected static void setInfo(ProgramInfo rowObject) {
		info = rowObject;
	}

	/** 
     * Update the row filter regular expression from the expression in
     * the text box.
     */
    private void newFilter() {
    	
        RowFilter<ProgramTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterText.getText(), 0,1,2);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
    
    private void open(ProgramInfo info)
    {
    	try {
			Desktop.getDesktop().open(info.getFile().getParentFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void openExe(String exe)
    {
    	try {
    		Runtime rt = Runtime.getRuntime() ;
    		rt.exec(exe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Fast SOFT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        FastSoft newContentPane = new FastSoft();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
