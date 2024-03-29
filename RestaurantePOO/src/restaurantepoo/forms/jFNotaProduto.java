/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jFNotaProduto.java
 *
 * Created on 01/12/2010, 03:00:44
 */

package restaurantepoo.forms;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import restaurantepoo.dao.ConfiguracaoDao;
import restaurantepoo.dao.MesaDao;
import restaurantepoo.dao.MesaProdutoDao;
import restaurantepoo.logica.Configuracao;
import restaurantepoo.logica.Mesa;
import restaurantepoo.logica.Produto;

/**
 *
 * @author Fernanda
 */
public class jFNotaProduto extends javax.swing.JFrame {

    /** Creates new form jFNotaProduto */
    public jFNotaProduto(jFMesas owner, String numMesa) throws SQLException, ParseException {
        initComponents();
        this.numMesa = numMesa;
        son = owner;
        populaTabelaProdutosBanco();
        imprimiCabecalho();
        //montaNota(numMesa);
    }

    jFMesas son;

    DefaultTableModel tmProduto = new DefaultTableModel(
            new Object [][]{
            },
            new String[]{"Quantidade", "Código", "Nome", "Preço", "Sub-total"}
     );

    ListSelectionModel lsmProdutos;
    String numMesa;
    Double somaTotal = 0.0;

    private void populaTabelaProdutosBanco() throws SQLException, ParseException {
        tmProduto.setRowCount(0);

        Mesa m1 = new Mesa();

        MesaDao daoMesa = new MesaDao();
        m1 = daoMesa.getLista(numMesa).get(0);

        MesaProdutoDao dao = new MesaProdutoDao();
        
        dao.getListaProdutosMesa(numMesa, m1);

        

        for (int i = 0; i < m1.produtos.size(); i++) {

            double preco = m1.produtos.get(i).getPreco();
            int qtd = Integer.parseInt(m1.quantidade.get(i));
            Double soma = qtd*preco;
            somaTotal += soma;

            tmProduto.addRow(new String[]{
                        String.valueOf(qtd),
                        String.valueOf(m1.produtos.get(i).getProduto()),
                        m1.produtos.get(i).getNome(),
                        String.valueOf(preco),
                        String.valueOf(soma)
                    });
        }
        total.setText(String.valueOf(somaTotal));
    }

    private void insereTabelaProdutos(Produto p1){

        tmProduto.addRow(new String[]{
            String.valueOf(p1.getProduto()),
            p1.getNome(),
            String.valueOf(p1.getPreco())
        });
    }

    private void imprimiCabecalho() throws SQLException{
        Configuracao config = new Configuracao();
        ConfiguracaoDao dao = new ConfiguracaoDao();

        dao.busca(config);

        cabecalho.setText(config.cabecalhoNota());
        cabecalho.setEditable(false);
    }

    public void atualizarFrameMesas() throws SQLException{
        //son.calculaValorTotal();
        son.populaTabelaProdutosBanco();

    //    System.out.println("Valor total recalculado: " + m1.getValorTotal());
        try {
            son.populaTabelaMesas("");
        } catch (SQLException ex) {
            Logger.getLogger(jFAdicionaProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(jFAdicionaProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cabecalho = new javax.swing.JTextArea();
        sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nota Fiscal Produtos");

        jTable1.setModel(tmProduto);
        jTable1.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("TOTAL: ");

        total.setText("jLabel2");

        cabecalho.setColumns(20);
        cabecalho.setRows(5);
        jScrollPane2.setViewportView(cabecalho);

        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total))
                    .addComponent(sair))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(sair)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        try {
            atualizarFrameMesas();
        } catch (SQLException ex) {
            Logger.getLogger(jFNotaProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_sairActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea cabecalho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton sair;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables

}
