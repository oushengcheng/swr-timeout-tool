(function (aps, $) {

    aps.noopFilter = function noopFilter(subString, value) {
        return true;
    };

    aps.marquee = function marquee(speed) {

        var total_height = $("#scrollwindow")[0].scrollHeight;
        //var visible_height = $("#scrollwindow").height();

        var scroll_window_top = $("#scrollwindow").position().top;
        var footer_top = $("#footer").position().top;

        var space = sum_of_bottom_spacing("#wrap");
        space = space + sum_of_bottom_spacing("#wrap > .container");
        space = space + sum_of_bottom_spacing(".planviewscroll");

        var visible_height = footer_top - scroll_window_top - space;

        $("#scrollwindow").css({'height' : visible_height});

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

    function sum_of_bottom_spacing(selector) {
        var space = $(selector).outerHeight(true) - $(selector).height();
        space = space / 2
        return space;
    }

}(window.aps = window.aps || {}, jQuery));