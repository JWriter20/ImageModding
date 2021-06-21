Please input your series of image modifications in one of the following formats:
create layer *name - creates a layer of name *name
current *savedName - sets the layer named *name as the one to be modified
load *filename - loads file into current
save *filename - saves *filename with its modifications
save-multi *filename *type - saves all the layers in a folder as type *type
load-multi *filename - loads in all the layers using a text file
*modification - modifies current with the given modification type
invisible *name - makes the layer with name *name invisible
visible *name - makes the layer with name *name visible

Descriptions: 
*name: Any String
*type: One of: "png", "jpg", "jpeg", or "ppm" (case-insensitive)
*savedName: represents the name of a *type that has already been created
*filename: the name of a file
*modification: a type of modification, one of: blur, sharp, sepia, greyscale