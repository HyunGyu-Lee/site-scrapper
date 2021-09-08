// Define Templates
// 다른 카드레이아웃으로 해보자 (한줄에 3개씩)
var CardTemplate = _.template(
    '<div class="card flex-md-row mb-4 shadow-sm h-md-250">' +
    '    <div class="card-body d-flex flex-column align-items-start">' +
    '        <strong class="d-inline-block mb-2 text-primary">Category(TODO)</strong>' +
    '        <h6 class="mb-0">' +
    '            <a class="text-dark" href="<%= url %>" target="_blank"><%= title %></a>' +
    '        </h6>' +
    '        <div class="mb-1 text-muted small">' +
    '            <%= dateToString(createAt) %>' +
    '        </div>' +
    '        <p class="card-text mb-auto"><%= description %></p>' +
    '        <a class="btn btn-outline-primary btn-sm" role="button" href="http://www.jquery2dotnet.com/">Continue reading</a>' +
    '    </div>' +
    '    <img class="card-img-right flex-auto d-none d-lg-block" alt="Thumbnail [200x250]" src="<%= image %>" style="width: 320px; height: 250px;">' +
    '</div>'
);

function dateToString(dateString) {
    var date = new Date(dateString);

    return date.getFullYear() + '-' +
        (date.getMonth() + 1) + '-' +
        date.getDate() + ' ' +
        date.getHours() + ':' +
        date.getMinutes() + ':' +
        date.getSeconds();
}

$(function () {
    $(document).on('click', '#btn-scrap', function () {
        createSlice($('#web-site-url').val());

        $('#web-site-url').val('');
    });

    searchSlices();
});

function searchSlices() {
    $.ajax({
        url: 'scrap',
        type: 'get',
        success: function (apiResponse) {
            var data = apiResponse.body;
            var size = data.length;
            var $scrapListArea = $('#scrap-list-area');
            $scrapListArea.empty();
            for (var i = 0; i < size; i++) {
                $scrapListArea.append(CardTemplate(data[i]));
            }
        }
    });
}

function searchSlicesBackup() {
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
            $listBody.empty();
            data.forEach(function (scrap) {
                $listBody.append(generateScrapTag(scrap));
            });
        }
    });
}

function createSlice(scrapUrl) {
    var data = JSON.stringify({
        url: scrapUrl
    });

    $.ajax({
        url: '/scrap',
        type: 'POST',
        contentType: "application/json",
        dataType: "json",
        data: data,
        success: function () {
            searchSlices();
        },
        error: function (e) {
            console.error(e);
            // todo 사용자에게 제목/설명을 입력받는 창을 띄워서 직접 입력하게한다. (썸넬 적절한거 찾아야함)
            alert('Error!')
        }
    })
}