import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

public class ImageProcessing {
	public static void main(String[] args) {
    // The provided images are apple.jpg, flower.jpg, and kitten.jpg
		
		int[][] imageData = imgToTwoD("./apple.jpg"); //Load image

    // Or load your own image using a URL!
		//int[][] imageData = imgToTwoD("https://content.codecademy.com/projects/project_thumbnails/phaser/bug-dodger.png");
		//viewImageData(imageData);
		
		int[][] trimmed = trimBorders(imageData, 60); //recortar
		twoDToImage(trimmed, "./trimmed_apple.jpg");

    int[][] negative = negativeColor(imageData);  //negativo
    twoDToImage(negative, "./negative_apple.jpg");

    int[][] horz = stretchHorizontally(imageData); //alargar
    twoDToImage(horz, "./horz_apple.jpg");

		int[][] vert = shrinkVertically(imageData); //achatar
    twoDToImage(vert, "./vert_apple.jpg");

		int[][] invert = invertImage(imageData); //rota 180
    twoDToImage(invert, "./invert_apple.jpg");

    int[][] filter = colorFilter(imageData,-75,30,-30); //filtro de color
    twoDToImage(filter, "./filter_apple.jpg");
    
    int[][] big = biggerImage(imageData,4); //agranda
    twoDToImage(big, "./big_apple.jpg");

		// int[][] allFilters = stretchHorizontally(shrinkVertically(colorFilter(negativeColor(trimBorders(invertImage(imageData), 50)), 200, 20, 40)));
		
		// Painting with pixels
		int [][] canvas = new int[500][500];

    int[][] randCanvas = paintRandomImage(canvas);
    twoDToImage(randCanvas, "./rand_canvas.jpg");

    int[] colorRGBA = {150,10,0,200};
    int color = getColorIntValFromRGBA(colorRGBA);
    int[][] rectangleCanvas = paintRectangle(canvas, 100, 50, 200, 300, color);
    twoDToImage(rectangleCanvas, "./rectangle_canvas.jpg");

    int[][] randRectangleCanvas = generateRectangles(canvas, 70);
    twoDToImage(randRectangleCanvas, "./rand_rectangles_canvas.jpg");
	}


	//IMAGE PROCESSING METHODS

	public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
	//Ingresa un array 2D de pixeles y la cantidad de pixeles a recortar
	//Retorna la imagen recortada

		if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
			int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
			for (int i = 0; i < trimmedImg.length; i++) {
				for (int j = 0; j < trimmedImg[i].length; j++) {
					trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
				}
			}
			return trimmedImg;
		} else {
			System.out.println("Cannot trim that many pixels from the given image.");
			return imageTwoD;
		}
	}

	public static int[][] negativeColor(int[][] imageTwoD) {
	//Ingresa un array 2D de pixeles 
	//Retorna la imagen en negativo

		int l=imageTwoD.length;
		int a=imageTwoD[0].length;
		
		int[][] negativeImage = new int[l][a];
		int[] pixelArr = new int[4]; //RGBA
		
		for(int i=0; i<l; i++){
			for(int j=0; j<a; j++){			
				pixelArr = getRGBAFromPixel(imageTwoD[i][j]);				
				pixelArr[0] = 255 - pixelArr[0]; //R
				pixelArr[1] = 255 - pixelArr[1]; //G
				pixelArr[2] = 255 - pixelArr[2]; //B
				
				negativeImage[i][j] = getColorIntValFromRGBA(pixelArr);
			}
		}		
		return negativeImage;
	}

	public static int[][] stretchHorizontally(int[][] imageTwoD) {
	//Ingresa un array 2D de pixeles 
	//Retorna la imagen alargada

		int l=imageTwoD.length;
		int a=imageTwoD[0].length;
		
		int[][] stretchImage = new int[l][2*a]
		
		for(int i=0; i<l; i++){
			for(int j=0; j<a; j++){			
				stretchImage[i][j*2] = imageTwoD[i][j];
				stretchImage[i][j*2+1] = imageTwoD[i][j];
			}
		}
		return stretchImage;
	}

	public static int[][] shrinkVertically(int[][] imageTwoD) {
	//Ingresa un array 2D de pixeles 
	//Retorna la imagen achatada

		int l=imageTwoD.length;
		int a=imageTwoD[0].length;
		
		int[][] shrinkImage = new int[l/2][a];
		
		for(int i=0; i<l/2; i++){
			for(int j=0; j<a; j++){			
				shrinkImage[i][j] = imageTwoD[i*2][j];
			}
		}		
		return shrinkImage;
	}

	public static int[][] invertImage(int[][] imageTwoD) {
	//Ingresa un array 2D de pixeles 
	//Retorna la imagen invertida

		int l=imageTwoD.length;
		int a=imageTwoD[0].length;
		
		int[][] invertImage = new int[l][a];
		
		for(int i=0; i<l; i++){
			for(int j=0; j<a; j++){			
				invertImage[l-i-1][a-j-1] = imageTwoD[i][j];
			}
		}		
		return invertImage;
	}
	
	public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
	//Ingresa un array 2D de pixeles 
	//Retorna la imagen con un color de filtro

		int l=imageTwoD.length;
		int a=imageTwoD[0].length;
		
		int[][] filterImage = new int[l][a];
		int[] pixelArr = new int[4];    //RGBA
		
		int[] pixelFilter = new int [3]; //RGB filter
		pixelFilter[0] = redChangeValue; //R
		pixelFilter[1] = greenChangeValue; //G
		pixelFilter[2] = blueChangeValue; //B
		
		for(int i=0; i<l; i++){
			for(int j=0; j<a; j++){			
				pixelArr = getRGBAFromPixel(imageTwoD[i][j]);				

				//Aplying filter
				for(int k=0; k<3; k++){
					int val = pixelArr[k] + pixelFilter[k];
					if(val > 255){
						val = 255;
					}
					else if (val < 0){
						val = 0;
					}
					pixelArr[k] = val;
				}

				filterImage[i][j] = getColorIntValFromRGBA(pixelArr);
			}
		}		
		return filterImage;
	}

	public static int[][] biggerImage(int[][] imageTwoD, int x) {
	//Ingresa un array 2D de pixeles 
	//Retorna la imagen agrandada

		int l=imageTwoD.length;
		int a=imageTwoD[0].length;
		
		int[][] bigImage = new int[x*l][x*a];
		
		for(int i=0; i<l; i++){
			for(int j=0; j<a; j++){					
				for(int m=0; m<x; m++){	//Copy pixels
					for(int n=0; n<x; n++){			
						bigImage[i*x+m][j*x+n] = imageTwoD[i][j];
					}
				}				
			}
		}
		return bigImage;
	}

	// Painting Methods
	public static int[][] paintRandomImage(int[][] canvas) {
		Random rand = new Random();
    int l=canvas.length;
		int a=canvas[0].length;
		
		int[] randArr = new int[4]; //rand
    randArr[3] = 255; //alpha

    for(int i=0; i<l; i++){
			for(int j=0; j<a; j++){			
        for(int k=0; k<3; k++){			
          randArr[k] =rand.nextInt(256); //0 to 255 RGB
        }				
				canvas[i][j] = getColorIntValFromRGBA(randArr);
			}
		}		
		return canvas;
	}

	public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {

    for(int i = rowPosition; i <= rowPosition + height; i++){ //rows
			for(int j = colPosition; j <= colPosition + width; j++){//cols					
				canvas[i][j] = color;
			}
		}		
		return canvas;
	}
	public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
		Random rand = new Random();
    int l=canvas.length;//row
		int a=canvas[0].length;//col
    int f = 3; //+1 minimum rand range
		
		int[] randArr = new int[4]; //rand rowPos, height, colPos, width
    int[] randRGBA = new int[4]; //rand RGBA color
    
    for(int n=0; n<numRectangles; n++){
      randArr[0] = rand.nextInt(l - f); //rows
      randArr[1] = rand.nextInt(l+1 - randArr[0]) + f; 
      randArr[2] = rand.nextInt(a - f); //cols
      randArr[3] = rand.nextInt(a+1 - randArr[2]) + f; 

      for(int m=0; m<4; m++){
        randRGBA[m] = rand.nextInt(256); //0 to 255 RGB
      }
      int color = getColorIntValFromRGBA(randRGBA); //RGBA to int color
      
      //make rectangle
      canvas = paintRectangle(canvas, randArr[3], randArr[1], randArr[0], randArr[2], color);

		return canvas;
	}


	// UTILITY METHODS

	public static int[][] imgToTwoD(String inputFileOrLink) {
	//Ingresa la imagen en formato archivo o link 
	//Retorna un array 2D de valores de cada pixel en la imagen

		try {
			BufferedImage image = null;
			if (inputFileOrLink.substring(0, 4).toLowerCase().equals("http")) {
				URL imageUrl = new URL(inputFileOrLink);
				image = ImageIO.read(imageUrl);
				if (image == null) {
					System.out.println("Failed to get image from provided URL.");
				}
			} else {
				image = ImageIO.read(new File(inputFileOrLink));
			}
			int imgRows = image.getHeight();
			int imgCols = image.getWidth();
			int[][] pixelData = new int[imgRows][imgCols];
			for (int i = 0; i < imgRows; i++) {
				for (int j = 0; j < imgCols; j++) {
					pixelData[i][j] = image.getRGB(j, i);
				}
			}
			return pixelData;
		} catch (Exception e) {
			System.out.println("Failed to load image: " + e.getLocalizedMessage());
			return null;
		}
	}

	public static void twoDToImage(int[][] imgData, String fileName) {
	//Ingresa un array 2D de pixeles y un nombre
	//Retorna un archivo imagen con nombre 

		try {
			int imgRows = imgData.length;
			int imgCols = imgData[0].length;
			BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < imgRows; i++) {
				for (int j = 0; j < imgCols; j++) {
					result.setRGB(j, i, imgData[i][j]);
				}
			}
			File output = new File(fileName);
			ImageIO.write(result, "jpg", output);
		} catch (Exception e) {d
			System.out.println("Failed to save image: " + e.getLocalizedMessage());
		}
	}

	public static int[] getRGBAFromPixel(int pixelColorValue) {
	//Ingresa el valor int del pixel de una imagen
	//Retorna un array R,G,B,A con valores entre el 0 y 255

		Color pixelColor = new Color(pixelColorValue);
		return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
	}

	public static int getColorIntValFromRGBA(int[] colorData) {
	//Ingresa un array R,G,B,A
	//Retorna el int del pixel de una imagen

		if (colorData.length == 4) {
			Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
			return color.getRGB();
		} else {
			System.out.println("Incorrect number of elements in RGBA array.");
			return -1;
		}
	}

	public static void viewImageData(int[][] imageTwoD) {
	//Ingresa los valores int del array de pixeles de la imagen
	//Retorna un array de 3x3 de la esquina izquierda de la imagen
 
		if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
			int[][] rawPixels = new int[3][3];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					rawPixels[i][j] = imageTwoD[i][j];
				}
			}
			System.out.println("Raw pixel data from the top left corner.");
			System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
			int[][][] rgbPixels = new int[3][3][4];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
				}
			}
			System.out.println();
			System.out.println("Extracted RGBA pixel data from top the left corner.");
			for (int[][] row : rgbPixels) {
				System.out.print(Arrays.deepToString(row) + System.lineSeparator());
			}
		} else {
			System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
		}
	}
}
