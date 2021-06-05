/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */

	private Pixel[][] pixels;
	private int height;
	private int width;

  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
    // Your solution here.
	  this.height = height;
	  this.width = width;
	  this.pixels = new Pixel[width][height];
	  for(int i=0;i<width; i++){
		  for(int j=0;j<height; j++){
		  pixels[i][j]= new Pixel();
		  }
	  }

  }

  /**
   * Pixel() is a private class that constructs a single pixel.
   * @param red, green, blue
   * @param pixel container
   */
  private class Pixel{
	  
	  private short red;
	  private short green;
	  private short blue;
	  private short[] pixel;
	  
	  /**
	   * Pixel() constructs a single pixel with rbg values of 0.
	   */
	 private Pixel() {
		 this.red = 0;
		 this.blue = 0;
		 this.green = 0;
		 this.pixel = new short[] {0,0,0};
		 
	 }
	 /**
	   * Pixel(r,g,b) constructs a single pixel with given rbg values.
	   */
	 private Pixel(short red, short green, short blue) {
		 this.red = red;
		 this.green = green;
		 this.blue = blue;
		 this.pixel = new short[] {red,green,blue};
		 
	 }
	 
	 private short getRed() {
		 return red;
	 }
	 
	 private short getBlue() {
		 return blue;
	 }
	 
	 private short getGreen() {
		 return green;
	 }
	 
	 public String toString() {
		 return " | "+ this.red + " " + this.green + " " + this.blue + " | ";
	 }
  }
  
  
  
  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    // Replace the following line with your solution.
    return this.width;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return this.height;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    // Replace the following line with your solution.
   return pixels[x][y].getRed();
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    // Replace the following line with your solution.
	  return pixels[x][y].getGreen();
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    // Replace the following line with your solution.
    return pixels[x][y].getBlue();
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here.
	  if((0<=red && red<=255)&& (0<=green && green<=255)&&(0<=blue && blue<=255)){
		  pixels[x][y] = new Pixel(red,green,blue);
	  }
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
    // Replace the following line with your solution.
	  String s = " ";
	  for(int i=0;i<this.width; i++){
		  for(int j=0;j<this.height; j++){
			  if(j==this.height-1) {
				  s = s + this.pixels[i][j]+"\n";  
			  }else {
				  s = s + this.pixels[i][j];  
			  }
			  
		  }
	  }
	  return s;
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
    // Replace the following line with your solution.
	  
	// case 0
	  if(numIterations<=0) {
		  return this;
	  }
	//create a copy of the original image
	  PixImage blurred = new PixImage(this.width,this.height);
	  
	//find neighbors
	  for(int i=0; i<height; i++){ // i: x-coordinate
		  for(int j=0;j<width; j++){ //j: y-coordinate
			  int[] x, y;
			  int avg;
			  int avgRed=0, avgGreen=0, avgBlue=0;
			  if(i==0) {
				  if(j==0) {//upper left
					  x = new int[] {0,1};
					  y = new int[] {0,1};
					  avg = 4;
				  }else if(j==width-1) {//lower left
					  x = new int[] {0,1};
					  y = new int[] {0,-1};
					  avg = 4;
				  }else {//middle left
					  x = new int[] {0,1,};
					  y = new int[] {-1,0,1};
					  avg = 6;
				  }
			  }else if(i==height-1) {
				  if(j==0) {//upper right
					  x = new int[] {0,-1};
					  y = new int[] {0,1};
					  avg = 4;
				  }else if(j==width-1) {//lower right
					  x = new int[] {0,-1};
					  y = new int[] {0,-1};
					  avg = 4;
				  }else {//middle right
					  x = new int[] {0,-1};
					  y = new int[] {0,1,-1};
					  avg = 6;
				  }
			  }else {
				  if(j==0){
					 x = new int[] {-1,0,1};
					 y = new int[] {0,1};
					 avg=6;
				  }else if(j== width-1) {
					  x = new int[] {-1,0,1};
					  y = new int[] {0,-1};
					  avg=6;
				  }else {
					  x= new int[]{0,1,-1};
					  y= new int[]{0,1,-1};
					  avg = 9;  
				  }
				 
			  }
			  //get avg 
			  for(int xi :x) {
				  for(int yi : y) {
					  avgRed = avgRed+this.getRed(j+yi,i+xi);
					  avgGreen = avgGreen+this.getGreen(j+yi,i+xi);
					  avgBlue = avgBlue+this.getBlue(j+yi,i+xi);
					  
				  }
			  }
			  
			  avgRed = avgRed/avg;
			  avgGreen = avgGreen/avg;
			  avgBlue = avgBlue/avg;
			  
			  //set new rbg value
			  blurred.setPixel(j, i, (short) avgRed, (short) avgGreen, (short) avgBlue);
			  
		  }
	  }
    return blurred.boxBlur(--numIterations);//recursive***
  }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {

	//create a copy of the original image
	  PixImage gray = new PixImage(this.width,this.height);
	  
	// create convolutions
	  int[]convolution1 =new int[]{1,0,-1,2,0,-2,1,0,-1};//for gx
	  int[]convolution2 =new int[]{1,2,1,0,0,0,-1,-2,-1};//for gy
	  
	 
	  for(int i=0; i<height; i++){ // i: x-coordinate
		  for(int j=0;j<width; j++){ //j: y-coordinate
			  int[] x, y;
			  int gxred=0,gyred=0,gxgreen=0,gygreen=0,gxblue=0,gyblue=0;
			  
			//find neighbor's location
			  if(i==0) {
				  if(j==0) {//upper left
					  x = new int[]{0,0,1,0,0,1,0,0,1};;
					  y = new int[]{0,0,0,0,0,0,1,1,1};
					 
				  }else if(j==width-1) {//lower left
					  x = new int[] {0,0,1,0,0,1,0,0,1};
					  y = new int[] {-1,-1,-1,0,0,0,0,0,0};
					  
				  }else {//middle left
					  x = new int[] {0,0,1,0,0,1,0,0,1};
					  y = new int[] {-1,-1,-1,0,0,0,1,1,1};
					
				  }
			  }else if(i==height-1) {
				  if(j==0) {//upper right
					  x = new int[] {-1,0,0,-1,0,0,-1,0,0};
					  y = new int[] {0,0,0,0,0,0,1,1,1};
					
				  }else if(j==width-1) {//lower right
					  x = new int[] {-1,0,0,-1,0,0,-1,0,0};
					  y = new int[] {-1,-1,-1,0,0,0,0,0,0};
					 
				  }else {//middle right
					  x = new int[] {-1,0,0,-1,0,0,-1,0,0};
					  y = new int[] {-1,-1,-1,0,0,0,1,1,1};
					
				  }
			  }else {
					  if(j==0){ //up middle
						 x = new int[] {-1,0,1,-1,0,1,-1,0,1};
						 y = new int[] {0,0,0,0,0,0,1,1,1};
						 
					  }else if(j== width-1) { // low middle
						  x = new int[] {-1,0,1,-1,0,1,-1,0,1};
						  y = new int[] {-1,-1,-1,0,0,0,0,0,0};
						  
					  }else {//middle middle
						  x= new int[]{-1,0,1,-1,0,1,-1,0,1};
						  y= new int[]{-1,-1,-1,0,0,0,1,1,1};
					  }
			  }
			  
			  //compute gradient
			  for (int p = 0; p <9; p++) {
				  gxred = gxred + convolution1[p]*this.getRed(j+y[p], i+x[p]);
				  gyred = gyred + convolution2[p]*this.getRed(j+y[p], i+x[p]);
				  gxgreen = gxgreen + convolution1[p]*this.getGreen(j+y[p], i+x[p]);
				  gygreen = gygreen + convolution2[p]*this.getGreen(j+y[p], i+x[p]);
				  gxblue = gxblue + convolution1[p]*this.getBlue(j+y[p], i+x[p]);
				  gyblue = gyblue + convolution2[p]*this.getBlue(j+y[p], i+x[p]);
				  
			  }
			  //compute energy
			  long mag = ((long)gxred)*((long)gxred)+((long)gyred)*((long)gyred)+
					  ((long)gxgreen)*((long)gxgreen)+((long)gygreen)*((long)gygreen)
					  +((long)gxblue)*((long)gxblue)+((long)gyblue)*((long)gyblue);
			  
			  //convert energy into grayscale intensity and set value
			  gray.setPixel(j, i, (short) mag2gray(mag), (short)  mag2gray(mag), (short)  mag2gray(mag));
		  }
	  }
	  return gray;
  }
		  


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());
    System.out.println(image1.sobelEdges());

    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.println(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  }
}
