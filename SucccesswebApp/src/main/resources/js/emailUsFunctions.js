/**
 * 
 */
function cleanEvilTags(data) {
    data = data.replace(/javascript/gi, "j&#097;v&#097;script");
    data = data.replace(/alert/gi, "&#097;lert");
    data = data.replace(/about:/gi, "&#097;bout:");
    data = data.replace(/onmouseover/gi, "&#111;nmouseover");
    data = data.replace(/onclick/gi, "&#111;nclick");
    data = data.replace(/onload/gi, "&#111;nload");
    data = data.replace(/onsubmit/gi, "&#111;nsubmit");
    data = data.replace(/<body/gi, "&lt;body");
    data = data.replace(/<html/gi, "&lt;html");
    data = data.replace(/document\./gi, "&#100;ocument.");
    data = data.replace(/<script/gi, "&lt;&#115;cript");
    return stripTags(data.trim());
}

function cleanData(data) {
    data = data.replace(/ & /g, " &amp; ");
    return data;
}

function multiDimensionalArrayMap(func, arr) {
    var newArr = {};
    if (arr) {
        Object.keys(arr).forEach(function (key) {
            var value = arr[key];
            newArr[key] = Array.isArray(value) ? multiDimensionalArrayMap(func, value) : func(value);
        });
    }
    return newArr;
}

// Placeholder function to strip tags
function stripTags(data) {
    
    // Regular expression pattern to match HTML tags
    var regex = /(<([^>]+)>)/ig;
  
    // Replace HTML tags with an empty string
    var strippedData = data.replace(regex, "");
  
  return strippedData;
}