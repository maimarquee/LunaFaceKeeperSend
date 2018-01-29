// Takes a data URI and returns the Data URI corresponding to the resized image at the wanted size.
function resizedataURL(datas, wantedWidth, wantedHeight) {
    // We create an image to receive the Data URI
    var img = document.createElement('img');

    // When the event "onload" is triggered we can resize the image.
    img.onload = function()
        {        
            // We create a canvas and get its context.
            var canvas = document.createElement('canvas');
            var ctx = canvas.getContext('2d');

            // We set the dimensions at the wanted size.
            canvas.width = wantedWidth;
            canvas.height = wantedHeight;

            // We resize the image with the canvas method drawImage();
            ctx.drawImage(this, 0, 0, wantedWidth, wantedHeight);

            var dataURI = canvas.toDataURL();

            /////////////////////////////////////////
            // Use and treat your Data URI here !! //
            /////////////////////////////////////////
        };

    // We put the Data URI in the image's src attribute
    img.src = datas;
}
// Use it like that : resizedataURL('yourDataURIHere', 50, 50);


function isValidCPNumber(cpNumber) {
	if(cpNumber.length == 11) {
		prefixArr = "0915~0927~0995~0938~0919~0813~0913~0981~0934~0922~0917~0935~0817~0939~0921~0907~0914~0998~0941~0923~0945~0936~0905~0940~0929~0908~0918~0999~0942~0924~0955~0976~0906~0946~0989~0909~0928~0951~0943~0931~0956~0997~0916~0948~0920~0910~0947~0912~0944~0932~0994~0975~0926~0950~0930~0911~0949~0970~0925~0933~0992~977~0978~0979~0996~0937~0973~0974~0959~0977".split("~");
		prefix = cpNumber.substring(0, 4);
		for(i=0; i<prefixArr.length; i++) {
			if(prefixArr[i] == prefix) {
				return true;
			}
		}
	}
	return false;
}