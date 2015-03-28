<div style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;margin: 0;font-family: Calibri,&quot;Open Sans&quot;,Helvetica,Arial,sans-serif;font-size: 14px;line-height: 1.81818181;color: #333333;background-color: #ffffff;height: 100%;">

    <div id="wrap" style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;min-height: 100%;height: auto;margin: 0 auto -60px;padding: 0 0 60px;">
        <div class="container" style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;margin-right: auto;margin-left: auto;padding-left: 15px;padding-right: 15px;padding: 60px 15px 0;">
            <div class="well planview" style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;min-height: 20px;padding: 19px;margin-bottom: 20px;background-color: rgba(181, 221, 247, 0.35);border: 1px solid #e3e3e3;border-radius: 1px;-webkit-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.05), 0 1px 0 rgba(255, 255, 255, 0.1);box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.05), 0 1px 0 rgba(255, 255, 255, 0.1);border-bottom-left-radius: 30px;border-bottom-right-radius: 30px;border-top-right-radius: 30px;border-top-left-radius: 30px;background-repeat: repeat-x;filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffe8e8e8', endColorstr='#fff5f5f5', GradientType=0);border-color: #F49712;border-width: 3px;">
                <a href=${staticurl}>${msg.click_here_for_the_live_site}</a>
                <h1 style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;font-size: 36px;margin: 0.67em 0;font-family: Calibri,&quot;Open Sans&quot;,Helvetica,Arial,sans-serif;font-weight: 500;line-height: 1.1;color: inherit;margin-top: 15px;margin-bottom: 6px;">
                     ${incident.title}
                </h1>
                <h3 style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;orphans: 3;widows: 3;page-break-after: avoid;font-family: Calibri,&quot;Open Sans&quot;,Helvetica,Arial,sans-serif;font-weight: 500;line-height: 1.1;color: inherit;margin-top: 15px;margin-bottom: 6px;font-size: 26px;">
                    <span id="timestamp" style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;font-size: 26px;">
                        ${msg.incident_view_lastpublished}
                        ${lastpublished}
                        -
                        ${msg.incident_view_nextreview}
                        ${nextreview}
                    </span>
                </h3>
                <br style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;">
                <div class="row clearfix" style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;margin-left: -15px;margin-right: -15px;">
                    <div class="col-xs-12" style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;position: relative;min-height: 1px;padding-left: 15px;padding-right: 15px;width: 100%;">
                        <span class="preformatted" style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;white-space: pre-wrap;font-size: 26px;">${incident.description}</span>
                        <hr style="-webkit-box-sizing: border-box;-moz-box-sizing: content-box;box-sizing: content-box;height: 0;margin-top: 25px;margin-bottom: 25px;border: 0;border-top: 1px solid #eeeeee;">
                        <ul style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;margin-top: 0;margin-bottom: 12.5px;">
                             <#list alterations as alteration>
                                <li style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;font-size: 26px;">
                                    ${alteration.longformat}
                                    <#if alteration.freeform?has_content>
                                        <br style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;">
                                        ${alteration.freeform}
                                    </#if>
                                </li>
                            </#list>
                        </ul>
                        <hr style="-webkit-box-sizing: border-box;-moz-box-sizing: content-box;box-sizing: content-box;height: 0;margin-top: 25px;margin-bottom: 25px;border: 0;border-top: 1px solid #eeeeee;">
                        <span class="preformatted" style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;white-space: pre-wrap;font-size: 26px;">${incident.footer}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="footer" style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;height: 60px;background-color: #002C50;">
        <div class="container" style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;margin-right: auto;margin-left: auto;padding-left: 15px;padding-right: 15px;">
            <div class="text-muted pull-right" style="-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;color: #999999;float: right !important;margin: 20px 0;">
                ${msg.incident_email_created} ${created}
            </div>
        </div>
    </div>
</div>
