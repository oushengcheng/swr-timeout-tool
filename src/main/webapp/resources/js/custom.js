(function (aps, $) {

    aps.noopFilter = function noopFilter(subString, value) {
        return true;
    };

    aps.marquee = function marquee(speed) {

        var total_height = $("#scrollwindow")[0].scrollHeight;
        var visible_height = $("#scrollwindow").height();


        console.log(total_height);
        console.log(visible_height);

        var count = 0;

        var duration = (visible_height + total_height) / speed;
        var refreshcount = Math.ceil(60000 / duration); // Refresh every minute or thereafter

        if (total_height > visible_height) {

            function scroller() {
                $("#marquee").css({'top': visible_height})
                             .animate({'top': -total_height},
                                       {'duration': duration,
                                       'easing': 'linear',
                                       'complete': progress});
            }

            function progress() {

                count++;

                if (count > refreshcount) {

                    location.reload(true);

                    count = 0;

                }

                scroller();
            }

            $("#marquee").css({'top': visible_height});

            scroller();

        } else {

            setTimeout(function() {location.reload(true);}, 60000);

        }

    };

}(window.aps = window.aps || {}, jQuery));