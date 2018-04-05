import java.awt.Color;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
	
	private Picture picture;
	private double[][] energy;
	private double[][]costV;
	private double[][]costH;
	
   public SeamCarver(Picture picture) {
	   if(picture == null) {
		   throw new IllegalArgumentException("argument picture is null!"); 
	   }
	this.picture = picture;   
   }               // create a seam carver object based on the given picture
   
   
   public Picture picture() {
	   return picture;
   }                         // current picture
   
   
   public     int width()  {
	  return picture().width(); 
   }                          // width of current picture
   
   
   public     int height() {
	  return picture().height();
   }                          // height of current picture
   
   
   public  double energy(int x, int y)  {
	   if(width() == 1 && height() == 1) {
		   if(x == 0 && y == 0) {
		   return 0;
		   }
	   }
	   if(x < 0 || x > width() - 1 || y < 0 || y > height() - 1) {
		   throw new IllegalArgumentException("x,y invalid in energy method!");
	   }
	   Color c1;
	   Color c2;
	   Color c3;
	   Color c4;
	   if(x != 0 && x != width() - 1) {
		   c1 = picture().get(x - 1, y);
		   c2 = picture().get(x + 1, y);
	   }
	  
	   else if(x == 0) {
		   c1 = picture().get(width() - 1, y);
		   c2 = picture().get(x + 1, y);
	   }
	   
	   else {
		   c1 = picture().get(x - 1, y);
		   c2 = picture().get(0, y);
	   }
	   
	   if(y != 0 && y != height() - 1) {
		   c3 = picture().get(x, y - 1);
		   c4 = picture().get(x, y + 1);
	   }
	  
	   else if(y == 0) {
		   c3 = picture().get(x, height() - 1);
		   c4 = picture().get(x, y + 1);
	   }
	   
	   else {
		   c3 = picture().get(x, y - 1);
		   c4 = picture().get(x, 0);
	   }
		   int r1 = c1.getRed();
		   int g1 = c1.getGreen();
		   int b1 = c1.getBlue();
		   
		   
		   int r2 = c2.getRed();
		   int g2 = c2.getGreen();
		   int b2 = c2.getBlue();
		   
		   int r1y = c3.getRed();
		   int g1y = c3.getGreen();
		   int b1y = c3.getBlue();
		   
		   
		   int r2y = c4.getRed();
		   int g2y = c4.getGreen();
		   int b2y = c4.getBlue();
		   
		   int u1 = (r2 - r1) * (r2 - r1);
		   int u2= (g2 - g1) * (g2 - g1);
		   int u3 = (b2 - b1) * (b2 - b1);
		   
		   int v1 = (r2y - r1y) * (r2y - r1y);
		   int v2= (g2y - g1y) * (g2y - g1y);
		   int v3 = (b2y - b1y) * (b2y - b1y);
		   
		   double enr = Math.sqrt(u1 + u2 + u3 + v1 + v2 + v3);
		   return enr;
   	   
   }             // energy of pixel at column x and row y
   
   private void CalculateEnergy(int h, int w) {
	   energy = new double[width()][height()];
	   for(int i = 0; i < h; i++) {
		   for(int j = 0; j < w; j++) {
			   energy[j][i] = energy(j, i);
			   //System.out.println("Energy[" + j + "][" + i + "] = " + energy[j][i]);
		   }
	   }
   }
   
   private void calculateCostV(int h, int w){
	   costV = new double[width()][height()];
	   CalculateEnergy(height(), width());
	   for(int i = 0; i < h; i++) {
		   for(int j = 0; j < w; j++) {
			if(i == 0) {
				costV[j][i] = energy[j][i];
				//System.out.println("costV[" + j + "][" + i + "] = " + costV[j][i]);
			}
			
			else if(j == 0) {
				costV[j][i] = energy[j][i] + 
						Math.min(costV[j][i - 1], costV[j + 1][i - 1]);
				//System.out.println("costV[" + j + "][" + i + "] = " + costV[j][i]);
			}
			
			else if(j == w - 1) {
				costV[j][i] = energy[j][i] + 
						Math.min(costV[j][i - 1], costV[j - 1][i - 1]);
				//System.out.println("costV[" + j + "][" + i + "] = " + costV[j][i]);
			}
			
			else {
				costV[j][i] = energy[j][i] + 
						Math.min((Math.min(costV[j][i - 1], costV[j - 1][i - 1])), 
								costV[j + 1][i - 1]);
				//System.out.println("costV[" + j + "][" + i + "] = " + costV[j][i]);
			}
		   }
	   }
   }
   
   //works for h > 1 && w > 1
   private int[] backTrackV(int h, int w) {
	   calculateCostV(height(), width());
	   int[] minSeqV = new int[h];
	   double min = Double.MAX_VALUE;
	   int lastInd = 0;
	   for(int i = 0; i < w; i++) {
		   if(costV[i][h - 1] < min) {
			   min = costV[i][h - 1];
			   lastInd = i;
		   }
	   }
	   minSeqV[h - 1] = lastInd;
	   int prevInd = lastInd;
	   
	   for(int i = h - 2; i >= 0; i--) {
		   double min1 = 0.0;
		   
		   if(prevInd > 0 && prevInd < w - 1) {
			  min1 = Math.min((Math.min(costV[prevInd][i], costV[prevInd - 1][i])), 
						costV[prevInd + 1][i]);    
			  if(min1 == costV[prevInd][i]) {
				  prevInd = prevInd;
				  minSeqV[i] = prevInd;
			  }
			  else if (min1 == costV[prevInd - 1][i]) {
				  prevInd = prevInd - 1;
				  minSeqV[i] = prevInd;
			  }
			  else if(min1 == costV[prevInd + 1][i]){
				 prevInd = prevInd + 1; 
				 minSeqV[i] = prevInd;
			  }
		   }
		   
		   else if(prevInd == 0) {
			   min1 = Math.min(costV[prevInd][i], costV[prevInd + 1][i]);
			   if(min1 == costV[prevInd][i]) {
				   prevInd = prevInd;
				   minSeqV[i] = prevInd;
			   }
			   else if(min1 == costV[prevInd + 1][i]) {
				   prevInd = prevInd + 1;
				   minSeqV[i] = prevInd;
			   }
		   }
		   
		   else {
			   min1 = Math.min(costV[prevInd][i], costV[prevInd - 1][i]);
			   if(min1 == costV[prevInd][i]) {
				   prevInd = prevInd;
				   minSeqV[i] = prevInd;
			   }
			   else if(min1 == costV[prevInd - 1][i]){
				   prevInd = prevInd - 1;
				   minSeqV[i] = prevInd;
			   }
		   }
		   
		   
	   }
	   return minSeqV;
   }
   
   private void calculateCostH(int h, int w){
	   costH = new double[width()][height()];
	   CalculateEnergy(height(), width());
	   for(int i = 0; i < w; i++) {
		   for(int j = 0; j < h; j++) {
			if(i == 0) {
				costH[i][j] = energy[i][j];
				//System.out.println("costH[" + i + "][" + j + "] = " + costH[i][j]);
			}
			
			else if(j == 0) {
				costH[i][j] = energy[i][j] + 
						Math.min(costH[i - 1][j], costH[i - 1][j + 1]);
				//System.out.println("costH[" + i + "][" + j + "] = " + costH[i][j]);
			}
			
			else if(j == h - 1) {
				costH[i][j] = energy[i][j] + 
						Math.min(costH[i - 1][j - 1], costH[i - 1][j]);
				//System.out.println("costH[" + i + "][" + j + "] = " + costH[i][j]);
			}
			
			else {
				costH[i][j] = energy[i][j] + 
						Math.min((Math.min(costH[i - 1][j], costH[i - 1][j - 1])), 
								costH[i - 1][j + 1]);
				//System.out.println("costH[" + i + "][" + j + "] = " + costH[i][j]);
			}
		   }
	   }
   }
   
 //works for h > 1 && w > 1
   private int[] backTrackH(int h, int w) {
	   calculateCostH(height(), width());
	   int[] minSeqH = new int[w];
	   double min = Double.MAX_VALUE;
	   int lastInd = 0;
	   for(int i = 0; i < h; i++) {
		   if(costH[w - 1][i] < min) {
			   min = costH[w - 1][i];
			   lastInd = i;
		   }
	   }
	   minSeqH[w - 1] = lastInd;
	   int prevInd = lastInd;
	   
	   //works for w > 1 && h > 1
	   for(int i = w - 2; i >= 0; i--) {
		   double min1 = 0.0;
		   
		   if(prevInd > 0 && prevInd < h - 1) {
			  min1 = Math.min((Math.min(costH[i][prevInd], costH[i][prevInd - 1])), 
						costH[i][prevInd + 1]);    
			  if(min1 == costH[i][prevInd]) {
				  prevInd = prevInd;
				  minSeqH[i] = prevInd;
			  }
			  else if (min1 == costH[i][prevInd - 1]) {
				  prevInd = prevInd - 1;
				  minSeqH[i] = prevInd;
			  }
			  else if(min1 == costH[i][prevInd + 1]) {
				 prevInd = prevInd + 1; 
				 minSeqH[i] = prevInd;
			  }
		   }
		   
		   else if(prevInd == 0) {
			   min1 = Math.min(costH[i][prevInd], costH[i][prevInd + 1]);
			   if(min1 == costH[i][prevInd]) {
				   prevInd = prevInd;
				   minSeqH[i] = prevInd;
			   }
			   else if(min1 == costH[i][prevInd + 1]) {
				   prevInd = prevInd + 1;
				   minSeqH[i] = prevInd;
			   }
		   }
		   
		   else {
			   min1 = Math.min(costH[i][prevInd], costH[i][prevInd - 1]);
			   if(min1 == costH[i][prevInd]) {
				   prevInd = prevInd;
				   minSeqH[i] = prevInd;
			   }
			   else if(min1 == costH[i][prevInd - 1]){
				   prevInd = prevInd - 1;
				   minSeqH[i] = prevInd;
			   }
		   }
		   
		   
	   }
	   return minSeqH;
   }
   
   public   int[] findHorizontalSeam() {
	   if(width() == 1 && height() == 1) {
		   int[] n = {0};
		   return n;
	   }
	   
	   else if(width() == 1 && height() > 1) {
		   double min = Double.MAX_VALUE;
		   int ind = 0;
		   for(int i = 0; i < height(); i++) {
			   if(costH[width()][i] < min) {
				   min = costH[width()][i];
				   ind = i;
			   }
			   int[] n = new int[1];
			   n[0] = ind;
			   return n;
		   }  
	   }
	   
	   else if(height() == 1) {
		   int[] n = new int[width()];
		   for(int i = 0; i < width(); i++) {
			   n[i] = 0;
		   }
		   return n;  
	   }
	   
	   return backTrackH(height(), width());
	   
   }              // sequence of indices for horizontal seam
   
   
   public   int[] findVerticalSeam() {
	   if(height() == 1 && width() == 1) {
		   int[] n = {0};
		   return n;
	   }
	   
	   else if(height() == 1 && width() > 1) {
		   double min = Double.MAX_VALUE;
		   int ind = 0;
		   for(int i = 0; i < width(); i++) {
			   if(costV[i][height()] < min) {
				   min = costV[i][height()];
				   ind = i;
			   }
			   int[] n = new int[1];
			   n[0] = ind;
			   return n;
		   }
	   }
	   
	   else if (width() == 1){
		   int[] n = new int[height()];
		   for(int i = 0; i < height(); i++) {
			   n[i] = 0;
		   }
		   return n;
	   }
	   
	   //reaching here means height > 1 && width > 1
	   return backTrackV(height(), width()); 
   }                // sequence of indices for vertical seam
   
   
   public    void removeHorizontalSeam(int[] seam) {
	   if(seam == null) {
		   throw new IllegalArgumentException("argument is null!"); 
	   }
	   if(height() == 1) {
		   throw new IllegalArgumentException("height=1 remove horizontal not allow!");
	   }
	   if(seam.length != width()) {
		   throw new IllegalArgumentException("horizontal seamarray of wrong size!");
	   }
	   for(int i = 0; i < seam.length - 1; i++) {
		   if(Math.abs(seam[i] - seam[i + 1]) > 1) {
			   throw new IllegalArgumentException("horizontal seamarray "
			   		+ "values differ by > 1");
		   }
	   }
	   Picture p = new Picture(width(), height() - 1);
	   for(int i = 0; i < p.width(); i++) {
		   int count = 0;
		  for(int j = 0; j < p.height() + 1; j++) {
			  if(j == seam[i]) {
				  count++;
				  if(j != p.height()) {
				  j++;
				  Color clr = picture().get(i, j);
				  int x = j - 1;
				  p.set(i, x, clr);
				  }
			  }
			  else{
				     Color clr = picture().get(i, j);
					 if(count == 1) {
					 int y = j - 1;
					 p.set(i, y, clr);
					 }
					 else {
						 p.set(i, j, clr);	 
					 }
			  }
		  }
	   }
	   
	   picture = p; 
   }  // remove horizontal seam from current picture
   
   
   public    void removeVerticalSeam(int[] seam) {
	   if(seam == null) {
		   throw new IllegalArgumentException("argument is null!"); 
	   }
	   if(width() == 1) {
		   throw new IllegalArgumentException("width=1 remove horizontal not allow!");
	   }
	   
	   if(seam.length != height()) {
		   throw new IllegalArgumentException("vertical seam array of wrong size!");
	   }
	   for(int i = 0; i < seam.length - 1; i++) {
		   if(Math.abs(seam[i] - seam[i + 1]) > 1) {
			   throw new IllegalArgumentException("vertical seam array "
			   		+ "values differ by > 1");
		   }
	   }
	   Picture p = new Picture(width() - 1, height());
	   for(int i = 0; i < p.height(); i++) {
		   int count = 0;
		  for(int j = 0; j < p.width() + 1; j++) {
			  
			  if(j == seam[i]) {
				  count++;
				  if(j != p.width()){
				  j++; 
				  Color clr = picture().get(j, i);
				  int x = j - 1;
				  p.set(x, i, clr);
				  }
			  }
			  else {
				 Color clr = picture().get(j, i);
				 if(count == 1) {
				 int y = j - 1;
				 p.set(y, i, clr);
				 }
				 else {
					 p.set(j, i, clr);	 
				 }
			  }
		  }
	   }
	   
	   picture = p;
   }    // remove vertical seam from current picture
  
   
}