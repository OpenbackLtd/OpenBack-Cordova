'use strict';

module.exports = function(ctx) {
	var fs = ctx.requireCordovaModule('fs');
	var path = ctx.requireCordovaModule('path');

	var rootDir = "";
	var srcFile = path.join(rootDir, "google-services.json");
	var destFile = path.join(rootDir, "platforms/android/google-services.json");
	
	fs.createReadStream(srcFile).pipe(fs.createWriteStream(destFile));
};