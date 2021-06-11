# ImageModing

## Image Citations:
* “PPMA Files Portable Pixel Map (ASCII).” PPMA Files, 22 July 2011, people.sc.fsu.edu/~jburkardt/data/ppma/snail.ascii.ppm. 
* “PPMA Files Portable Pixel Map (ASCII).” PPMA Files, 22 July 2011, people.sc.fsu.edu/~jburkardt/data/ppma/sines.ascii.ppm. 

## Model Design Overview

#### ImageClasses
* CustomCheckerBoard - A custom type of image that creates an image of a checkerboard based on the inputs.
* PPMImage - Initializes an Image from a PPM image, which is essentially a large file of RGB values.
* RainbowImage - A custom type of image that creates an image representing a rainbow using the standard colors one would expect.
* ImageUtil - A provided file that helps read in PPM files.
* AbstractImage - The abstract class that implements the methods of the image, extended in order to create more custom Images.
* Image - The interface that provides all of the methods to be implemented by AbstractImage, makes an Abstract image open to modifaction via the classes in the Modifications folder.

#### Modifications
* Blurry - A class that provides the kernal's values in the form of a matrix used to make an Image more blurry.
* Sharpen - A class that provides the kernal's values in the form of a matrix used to make an Image sharper.
* Sepia - A class that sets a matrix's values for adjusting a photo's RGB values to give it a sepia tone.
* Greyscale - A class that sets a matrix's values for adjusting a photo's RGB values to make it black and white (greyscaled).
* Filter - Uses the given kernal values to adjust an image  
* Transform - Uses the given matrix to adjust the Color value of an image.
* IModifyImage - the interface that wrapps Filter and Transform, used to apply the filters to images.
