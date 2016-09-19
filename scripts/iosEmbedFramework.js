'use strict';

module.exports = function(ctx) {
    var fs = ctx.requireCordovaModule('fs'),
        path = ctx.requireCordovaModule('path'),
        xcode = ctx.requireCordovaModule('xcode');

    function getXCodeProjectIn(root) {
        var files = fs.readdirSync(root);
        for (var index = 0; index < files.length; index++) {
            var filename = path.join(root, files[index]);
            if (filename.endsWith(".xcodeproj")) {
                return path.join(filename, "project.pbxproj");
            }
        }
        return "";
    };

    var platformRoot = path.join(ctx.opts.projectRoot, 'platforms/ios'),
        projectPath = getXCodeProjectIn(platformRoot),
        pluginRoot = path.join(ctx.opts.projectRoot, 'plugins/cordova-plugin-openback/lib/ios'),
        pluginPath = path.join(pluginRoot, 'OpenBack.framework');

    var project = xcode.project(projectPath);
    project.parseSync();
    if (!project.pbxEmbedFrameworksBuildPhaseObj()) {
        project.addBuildPhase([], 'PBXCopyFilesBuildPhase', 'Embed Frameworks', project.getFirstTarget().uuid, 'frameworks');
    }

    project.addFramework(pluginPath, { 'embed': true, 'customFramework': true});
    fs.writeFileSync(projectPath, project.writeSync());
    console.log("Added OpenBack.framework to Embed Frameworks");
};