import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.princeton.cs.algs4.Picture;

public class SeamCarverTest {

	@Test
	public void testfindSeamVertical6x5() {
		Picture pic = new Picture("pic.png");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findVerticalSeam(); 
		sc.removeVerticalSeam(cmp);
		
		assertEquals("", cmp[0], 3);
		assertEquals("", cmp[1], 4);
		assertEquals("", cmp[2], 3);
		assertEquals("", cmp[3], 2);
		assertEquals("", cmp[4], 2);
		int[] cmp1 = sc.findVerticalSeam(); 
		
		assertEquals("", sc.width(), 5);
		assertEquals("", cmp1[0], 3);
		assertEquals("", cmp1[1], 2);
		assertEquals("", cmp1[2], 1);
		assertEquals("", cmp1[3], 2);
		assertEquals("", cmp1[4], 2);
		
	}
	
	@Test
	public void testRemoveSeamVertical6x5() {
		Picture pic = new Picture("pic.png");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findVerticalSeam(); 
		sc.removeVerticalSeam(cmp);
		int[] cmp1 = sc.findVerticalSeam(); 
		
		assertEquals("", sc.width(), 5);
		assertEquals("", cmp1[0], 3);
		assertEquals("", cmp1[1], 2);
		assertEquals("", cmp1[2], 1);
		assertEquals("", cmp1[3], 2);
		assertEquals("", cmp1[4], 2);
		
	}
	
	@Test
	public void testRemoveSeamHorizontal6x5() {
		Picture pic = new Picture("pic.png");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		sc.removeHorizontalSeam(cmp);
		int[] cmp1 = sc.findHorizontalSeam(); 
		
		assertEquals("", sc.height(), 4);
		assertEquals("", cmp1[0], 2);
		assertEquals("", cmp1[1], 1);
		assertEquals("", cmp1[2], 1);
		assertEquals("", cmp1[3], 0);
		assertEquals("", cmp1[4], 1);
		assertEquals("", cmp1[5], 1);
		
	}
	
	@Test
	public void testfindSeamHorizontal6x5() {
		Picture pic = new Picture("pic.png");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		
		assertEquals("", cmp[0], 2);
		assertEquals("", cmp[1], 2);
		assertEquals("", cmp[2], 1);
		assertEquals("", cmp[3], 2);
		assertEquals("", cmp[4], 1);
		assertEquals("", cmp[5], 2);
	}
	

	@Test
	public void testOwnPicEnergy() {
		Color c00 = new Color(155, 145, 122);
		Color c01 = new Color(105, 125, 111);
		Color c02 = new Color(70, 250, 45);
		Color c10 = new Color(189, 212, 100);
		Color c11 = new Color(125, 15, 50);
		Color c12 = new Color(255, 250, 145);
		Color c20 = new Color(210, 165, 22);
		Color c21 = new Color(10, 145, 222);
		Color c22 = new Color(70, 50, 45);
		
		Picture p = new Picture(3,3);
		
		p.set(0, 0, c00);
		p.set(0, 1, c01);
		p.set(0, 2, c02);
		p.set(1, 0, c10);
		p.set(1, 1, c11);
		p.set(1, 2, c12);
		p.set(2, 0, c20);
		p.set(2, 1, c21);
		p.set(2, 2, c22);
		
		SeamCarver sc = new SeamCarver(p);
		int[] cmp1 = sc.findVerticalSeam();
		int[] cmp = sc.findHorizontalSeam();
		assertEquals("", Math.round(sc.energy(0, 0)), 173);
		assertEquals("", Math.round(sc.energy(1, 1)), 172);
		assertEquals("", Math.round(sc.energy(2, 2)), 353);
		assertEquals("", cmp[0], 0);
		assertEquals("", cmp[1], 1);
		assertEquals("", cmp[2], 1);
		
		assertEquals("", cmp1[0], 0);
		assertEquals("", cmp1[1], 1);
		assertEquals("", cmp1[2], 1);
	
	}
	
	@Test
	public void testOwnPicfindhorizontal() {
		Color c00 = new Color(155, 145, 122);
		Color c01 = new Color(105, 125, 111);
		Color c02 = new Color(70, 250, 45);
		Color c10 = new Color(189, 212, 100);
		Color c11 = new Color(125, 15, 50);
		Color c12 = new Color(255, 250, 145);
		Color c20 = new Color(210, 165, 22);
		Color c21 = new Color(10, 145, 222);
		Color c22 = new Color(70, 50, 45);
		
		Picture p = new Picture(3,3);
		
		p.set(0, 0, c00);
		p.set(0, 1, c01);
		p.set(0, 2, c02);
		p.set(1, 0, c10);
		p.set(1, 1, c11);
		p.set(1, 2, c12);
		p.set(2, 0, c20);
		p.set(2, 1, c21);
		p.set(2, 2, c22);
		
		SeamCarver sc = new SeamCarver(p);
		int[] cmp = sc.findHorizontalSeam();
		
		assertEquals("", cmp[0], 0);
		assertEquals("", cmp[1], 1);
		assertEquals("", cmp[2], 1);
	}
	
	@Test
	public void testOwnPicfindvertical() {
		Color c00 = new Color(155, 145, 122);
		Color c01 = new Color(105, 125, 111);
		Color c02 = new Color(70, 250, 45);
		Color c10 = new Color(189, 212, 100);
		Color c11 = new Color(125, 15, 50);
		Color c12 = new Color(255, 250, 145);
		Color c20 = new Color(210, 165, 22);
		Color c21 = new Color(10, 145, 222);
		Color c22 = new Color(70, 50, 45);
		
		Picture p = new Picture(3,3);
		
		p.set(0, 0, c00);
		p.set(0, 1, c01);
		p.set(0, 2, c02);
		p.set(1, 0, c10);
		p.set(1, 1, c11);
		p.set(1, 2, c12);
		p.set(2, 0, c20);
		p.set(2, 1, c21);
		p.set(2, 2, c22);
		
		SeamCarver sc = new SeamCarver(p);
		int[] cmp1 = sc.findVerticalSeam();
		
		assertEquals("", cmp1[0], 0);
		assertEquals("", cmp1[1], 1);
		assertEquals("", cmp1[2], 1);
	}
	
	@Test
	public void testfindSeamVerticalAndWidth800x800() {
		Picture pic = new Picture("pic1.png");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findVerticalSeam(); 
		sc.removeVerticalSeam(cmp);
		assertEquals("", sc.width(), 799);
		
	}
	
	@Test
	public void testfindSeamHorizontalAndHeight800x800() {
		Picture pic = new Picture("pic1.png");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		sc.removeHorizontalSeam(cmp);
		assertEquals("", sc.height(), 799);
		
	}
	
	@Test
	public void testfindRemoveH200x100() {
		Picture pic = new Picture("200x100.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		sc.removeHorizontalSeam(cmp);
		int[] cmp1 = sc.findVerticalSeam();
		sc.removeVerticalSeam(cmp1);

	}
	
	@Test
	public void testfindRemoveV200x100() {
		Picture pic = new Picture("200x100.jpg");
		SeamCarver sc = new SeamCarver(pic);
		
		int[] cmp1 = sc.findVerticalSeam();
		sc.removeVerticalSeam(cmp1);

	}
	
	@Test
	public void testfindRemoveH200x200() {
		Picture pic = new Picture("200x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		sc.removeHorizontalSeam(cmp);

	}
	
	@Test
	public void testfindRemoveV200x200() {
		Picture pic = new Picture("200x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp1 = sc.findVerticalSeam();
		sc.removeVerticalSeam(cmp1);

	}
	
	@Test
	public void testfindRemoveH200x400() {
		Picture pic = new Picture("200x400.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		sc.removeHorizontalSeam(cmp);
		

	}
	
	@Test
	public void testfindRemoveV200x400() {
		Picture pic = new Picture("200x400.jpg");
		SeamCarver sc = new SeamCarver(pic);
		
		int[] cmp1 = sc.findVerticalSeam();
		sc.removeVerticalSeam(cmp1);

	}
	
	@Test
	public void testfindRemoveH200x800() {
		Picture pic = new Picture("200x800.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		sc.removeHorizontalSeam(cmp);


	}
	
	@Test
	public void testfindRemoveV200x800() {
		Picture pic = new Picture("200x800.jpg");
		SeamCarver sc = new SeamCarver(pic);
		
		int[] cmp1 = sc.findVerticalSeam();
		sc.removeVerticalSeam(cmp1);

	}
	
	@Test
	public void testfindRemoveH200x1600() {
		Picture pic = new Picture("200x1600.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		sc.removeHorizontalSeam(cmp);
		

	}
	
	@Test
	public void testfindRemoveV200x1600() {
		Picture pic = new Picture("200x1600.jpg");
		SeamCarver sc = new SeamCarver(pic);
		
		int[] cmp1 = sc.findVerticalSeam();
		sc.removeVerticalSeam(cmp1);

	}
	
	@Test
	public void testfindRemoveH100x200() {
		Picture pic = new Picture("100x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		sc.removeHorizontalSeam(cmp);
		

	}
	
	@Test
	public void testfindRemoveV100x200() {
		Picture pic = new Picture("100x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		
		int[] cmp1 = sc.findVerticalSeam();
		sc.removeVerticalSeam(cmp1);

	}
	
	@Test
	public void testfindRemoveH400x200() {
		Picture pic = new Picture("400x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		sc.removeHorizontalSeam(cmp);


	}
	
	@Test
	public void testfindRemoveV400x200() {
		Picture pic = new Picture("400x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		
		int[] cmp1 = sc.findVerticalSeam();
		sc.removeVerticalSeam(cmp1);

	}
	
	@Test
	public void testfindRemoveH800x200() {
		Picture pic = new Picture("800x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		sc.removeHorizontalSeam(cmp);
		

	}
	
	@Test
	public void testfindRemoveV800x200() {
		Picture pic = new Picture("800x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		
		int[] cmp1 = sc.findVerticalSeam();
		sc.removeVerticalSeam(cmp1); 

	}
	
	@Test
	public void testfindRemoveH1600x200() {
		Picture pic = new Picture("1600x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] cmp = sc.findHorizontalSeam(); 
		sc.removeHorizontalSeam(cmp);
		

	}
	
	@Test
	public void testfindRemoveV1600x200() {
		Picture pic = new Picture("1600x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		
		int[] cmp1 = sc.findVerticalSeam();
		sc.removeVerticalSeam(cmp1);

	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testnullConstructor(){
	    SeamCarver sc = new SeamCarver(null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testWrongDimensionForEnergy(){
		Picture pic = new Picture("1600x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		sc.energy(-1, 2100);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testWrongSeamArray1(){
		Picture pic = new Picture("1600x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] n = {1,2};
		sc.removeHorizontalSeam(n);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testWrongSeamArray2(){
		Picture pic = new Picture("pic.jpg");
		SeamCarver sc = new SeamCarver(pic); 
		int[] n = {1,2,2,4,3,2};
		sc.removeHorizontalSeam(n);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testWrongSeamArray3(){
		Picture pic = new Picture("pic.jpg");
		SeamCarver sc = new SeamCarver(pic); 
		int[] n = {1,2,2,4,3};
		sc.removeVerticalSeam(n);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testWrongSeamArray4(){
		Picture pic = new Picture("pic.jpg");
		SeamCarver sc = new SeamCarver(pic); 
		int[] n = {1,2,2,4,3,2};
		sc.removeVerticalSeam(n);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testWrongSeamArray5(){
		Picture pic = new Picture("pic.jpg");
		SeamCarver sc = new SeamCarver(pic); 
		int[] n = {1,2,2,4,3};
		sc.removeVerticalSeam(n);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testWrongSeamArray6(){
		Picture pic = new Picture("1600x200.jpg");
		SeamCarver sc = new SeamCarver(pic);
		int[] n = {1,2};
		sc.removeHorizontalSeam(n);
	}
	
	

}
