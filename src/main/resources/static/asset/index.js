$(function () {
    $(document).on('click', '#btn-scrap', function () {
        createSlice($('#web-site-url').val());
    });

    searchSlices();
});

function searchSlices() {
    var generateScrapTag = function (scrap) {
        return '<tr>' +
                   '<td>' + scrap.id + '</td>' +
                   '<td><a href="' + scrap.url + '">' + scrap.url + '</a></td>' +
                   '<td>' + scrap.title + '</td>' +
                   '<td>' + scrap.createAt + '</td>' +
                   '<td><img src="' + scrap.image + '" style="height:180px;"></td>' +
                   '<td>' + scrap.description + '</td>' +
               '</tr>';
    };

    $.ajax({
        url: 'scrap',
        type: 'get',
        success: function (apiResponse) {
            var $table = $('#web-slice-table');
            var $listBody = $table.find('tbody');
            var data = apiResponse.body;

            data.forEach(function (scrap) {
                $listBody.append(generateScrapTag(scrap));
            });
        }
    });
}

function createSlice(url) {
    alert('스크랩 for ' + url);
}