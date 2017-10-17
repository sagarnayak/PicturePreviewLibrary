# PicturePreviewLibrary
library for viewing your list of pictures in a beautiful and fluid UI without doing much work.

min sdk version - 21
compile sdk version - 26

To add this to your project follow the steps below.

1. In project level gradle

        allprojects {
		  repositories {
			...
			maven { url 'https://jitpack.io' }
		  }
	    }

2. In app level gradle

       dependencies {
		com.github.sagarnayak:PicturePreviewLibrary:1.0
	}
	
3. After adding all this dependency to your project call initialise the library. you can provide 4 parameters to the library.
     1. Context (Mandatory)
     2. Dataset For Display (Mandatory)
     		This is the arraylist of AdapterDataPojo. which is the pojo provided by the library for putting the picture data (url and picture details) into it and send to the library.
     3. Actionbar color in picture preview (Optional)
     4. Actionbar Title in picture preview (Optional)
      
            ArrayList<AdapterDataPojo> adapterDataPojos = new ArrayList<>();
	    
            adapterDataPojos.add(new AdapterDataPojo("https://une.jpg", "Pic details"));
            adapterDataPojos.add(new AdapterDataPojo("https://imgate.jpg", "Pic details"));
	    new PicturePreview(MainActivity.this, adapterDataPojos);
	    
4. after initializing the data to the PicturePreview class call the start() to start the library.

       new PicturePreview(MainActivity.this, adapterDataPojos);
       
And you are all set to use the library. you can also get a demo of the library by cloning this repo to your local machine. just feel free to use it and experiment on it.
