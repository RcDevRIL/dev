var lcov2badge = require('lcov2badge');
lcov2badge.badge('../../cesi_covid_19_tracker/coverage/lcov.info', function(err, svgBadge){
    if (err) throw err;
    console.log(svgBadge);
});

//var options = {
//    filePath: './coverage/lcov.info',
//    okColor: 'green', 					// default is 'brightgreen'
//    warnColor: 'yellow', 				// default is 'orange'
//    koColor: 'orange', 					// default is 'red'
//    warnThreshold: 90, 					// default is 80
//    koThreshold: 70,					// default is 60
//    subject: 'cover'					// default is 'coverage'
//};
//lcov2badge.badge(options, function(err, svgBadge){...});