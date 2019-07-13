
    // get reference to the destination drop down
    let dropdown = document.getElementById('to');
    dropdown.length = 0;

    // the default option which is displayed when there is no selection
    let defaultOption = document.createElement('option');
    defaultOption.text = 'Seleccionar destino';

    // add default option to drop down
    dropdown.add(defaultOption);
    dropdown.selectedIndex = 0;

    // url which contains data for drop down
    // just a sample url
    const url = 'https://api.myjson.com/bins/7xq2x';

    // create a async request for fetching the data
    const request = new XMLHttpRequest();
    request.open('GET', url, true);

    request.onload = function () {
        // http OK
        if (request.status === 200) {
            const data = JSON.parse(request.responseText);
            let option;
            
            // loop through the result data, and add the items
            // to the drop down
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].name;
                option.value = data[i].abbreviation;
                dropdown.add(option);
            }
        } else {
            // Reached the server, but it returned an error
        }
    }

    request.onerror = function () {
        console.error('An error occurred fetching the JSON from ' + url);
    };

    request.send();