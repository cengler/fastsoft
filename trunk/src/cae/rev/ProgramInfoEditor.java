package cae.rev;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import cae.ProgramInfo;


@SuppressWarnings("serial")
public class ProgramInfoEditor extends JPanel {

    protected JTable table;
    protected JScrollPane scroller;
    protected ProgramInfoEditorModel tableModel;
    protected ProgramInfo p;

    public ProgramInfoEditor(ProgramInfo p) {
        initComponent(p);
        this.p = p;
    }

    public void initComponent(ProgramInfo p) {
        tableModel = new ProgramInfoEditorModel(p);
        table = new JTable();
        table.setModel(tableModel);
        table.addMouseListener(new MyMouseAdapter());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));

        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }
    
    private class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent evt)
        {
            int x = evt.getX();
            int y = evt.getY();
            int row = table.rowAtPoint(new Point(x,y));
            int col = table.columnAtPoint(new Point(x,y));
            if (row == ProgramInfoEditorModel.KEYGEN_IDX)
            {
            	File initPath = p.getFile();
            	if(p.getKeygen() != null)
            		initPath = p.getKeygen(); 
                File value = load(initPath, p.getKeygen()); 
                if (value!=null)
                {
                	tableModel.setValueAt(value,row,col);
                }
            }
            if (row == ProgramInfoEditorModel.EXE_IDX)
            {
            	File initPath = p.getFile();
            	if(p.getExe() != null)
            		initPath = p.getExe(); 
                File value = load(initPath, p.getExe()); 
                if (value!=null)
                {
                	tableModel.setValueAt(value,row,col);
                }
            }
        }
    }
    
    private File load(File path, File oldSelected)
    {
        JFileChooser chooser = new JFileChooser(path);
        chooser.setDialogTitle("Open");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File file = chooser.getSelectedFile();
            if (file!= null)
            {
                return file;
            }
            else 
            {
                return null;
            }
        }
        return oldSelected;
    }
}