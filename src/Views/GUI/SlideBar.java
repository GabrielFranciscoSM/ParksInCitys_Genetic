/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views.GUI;

/**
 *
 * @author gabriel
 */
public class SlideBar extends javax.swing.JPanel {

    /**
     * Creates new form prueba
     */

    public SlideBar() {        
        initComponents();
    }
    
    public void setLimits(int max, int min){
        PopSizeSlider.setMaximum(max);
        PopSizeSlider.setMinimum(min);
        
        PopSizeMaxLabel.setText(Integer.toString(max));
        PopSizeMinLabel.setText(Integer.toString(min));
    }
    
    public void setDefault(int def){
        PopSizeSlider.setValue(def);
    }
    
    public int getValue(){
        return PopSizeSlider.getValue();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PopSizeMinLabel = new javax.swing.JLabel();
        PopSizeMidLabel1 = new javax.swing.JLabel();
        PopSizeMaxLabel = new javax.swing.JLabel();
        PopSizeSlider = new javax.swing.JSlider();

        PopSizeMinLabel.setText("MIN");

        PopSizeMidLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PopSizeMidLabel1.setText("MID");

        PopSizeMaxLabel.setText("MAX");

        PopSizeSlider.setValue(10);
        PopSizeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PopSizeSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PopSizeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PopSizeMinLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PopSizeMidLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PopSizeMaxLabel))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PopSizeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PopSizeMinLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PopSizeMidLabel1)
                        .addComponent(PopSizeMaxLabel))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PopSizeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PopSizeSliderStateChanged
        PopSizeMidLabel1.setText(Integer.toString(PopSizeSlider.getValue()));
    }//GEN-LAST:event_PopSizeSliderStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PopSizeMaxLabel;
    private javax.swing.JLabel PopSizeMidLabel1;
    private javax.swing.JLabel PopSizeMinLabel;
    private javax.swing.JSlider PopSizeSlider;
    // End of variables declaration//GEN-END:variables
}
