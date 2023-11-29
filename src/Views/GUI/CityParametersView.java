/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views.GUI;

import Model.Individuals.CityTileset;
import Model.Inicializer.InicializerController;
import Model.Inicializer.RandomCityInicializer;
import Model.Inicializer.RandomParkInicializer;

/**
 *
 * @author gabriel
 */
public class CityParametersView extends javax.swing.JPanel {

    /**
     * Creates new form CityParametersView
     */
    public CityParametersView() {
        initComponents();
        
        CitySlide.setLimits(CityTileset.MAXSIZE, CityTileset.MINSIZE);
        CitySlide.setDefault(CityTileset.DEFAULTSIZE);
        
        roadDensitySlide.setLimits(RandomCityInicializer.MAXROADDENSITY, RandomCityInicializer.MINROADDENSITY);
        roadDensitySlide.setDefault(RandomCityInicializer.DEFROADDENSITY);
        
        buildingDensitySlider.setLimits(RandomCityInicializer.MAXBUILDINGDENSITY,RandomCityInicializer.MINBUILDINGDENSITY);
        buildingDensitySlider.setDefault(RandomCityInicializer.DEFBUILDINGDENSITY);
        
        parksSpreadnessSlider.setLimits(RandomParkInicializer.MAXPARKSPREADNESS, RandomParkInicializer.MINPARKSPREADNESS);
        parksSpreadnessSlider.setDefault(RandomParkInicializer.DEFPARKSPREADNESS);
        
        maxParksSlider.setLimits(100, 1);
        maxParksSlider.setDefault(InicializerController.DEFPARKSPARCENTAGE);
    }
    
    public int getCitySizeValue(){
        return CitySlide.getValue();
    }
    
    public int getRoadDensity(){
        return roadDensitySlide.getValue();
    }
    
    public int getBuildingDensity(){
        return buildingDensitySlider.getValue();
    }
    
    public int getParkSpreadness(){
        return parksSpreadnessSlider.getValue();
    }
    
    public int getParksPercentage(){
        return maxParksSlider.getValue();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parksSpreadnessLabel = new javax.swing.JLabel();
        roadDensityLabel = new javax.swing.JLabel();
        CitySizeLabel = new javax.swing.JLabel();
        CitySlide = new Views.GUI.SlideBar();
        roadDensitySlide = new Views.GUI.SlideBar();
        buildingDensitySlider = new Views.GUI.SlideBar();
        jSeparator1 = new javax.swing.JSeparator();
        buildingDensityLabel1 = new javax.swing.JLabel();
        parksSpreadnessSlider = new Views.GUI.SlideBar();
        maxParksSlider = new Views.GUI.SlideBar();
        maxParksLabel = new javax.swing.JLabel();

        parksSpreadnessLabel.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        parksSpreadnessLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        parksSpreadnessLabel.setText("Parks Spreadness");

        roadDensityLabel.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        roadDensityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roadDensityLabel.setText("Road Density");

        CitySizeLabel.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        CitySizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CitySizeLabel.setText("City Size");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        buildingDensityLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        buildingDensityLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buildingDensityLabel1.setText("Building Density");

        maxParksLabel.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        maxParksLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maxParksLabel.setText("Cuantity of parks");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roadDensityLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(CitySizeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buildingDensityLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maxParksLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(parksSpreadnessLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CitySlide, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(roadDensitySlide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(buildingDensitySlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(parksSpreadnessSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(maxParksSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(CitySizeLabel))
                    .addComponent(CitySlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(roadDensitySlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(roadDensityLabel)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(buildingDensitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(parksSpreadnessSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(buildingDensityLabel1)
                        .addGap(32, 32, 32)
                        .addComponent(parksSpreadnessLabel)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(maxParksSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(maxParksLabel)))
                .addContainerGap(190, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CitySizeLabel;
    private Views.GUI.SlideBar CitySlide;
    private javax.swing.JLabel buildingDensityLabel1;
    private Views.GUI.SlideBar buildingDensitySlider;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel maxParksLabel;
    private Views.GUI.SlideBar maxParksSlider;
    private javax.swing.JLabel parksSpreadnessLabel;
    private Views.GUI.SlideBar parksSpreadnessSlider;
    private javax.swing.JLabel roadDensityLabel;
    private Views.GUI.SlideBar roadDensitySlide;
    // End of variables declaration//GEN-END:variables
}
