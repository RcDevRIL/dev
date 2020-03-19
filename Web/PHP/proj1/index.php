<?php


$page = $_GET['page'];

switch($page) {
    case 'events';
        $events = ['virus', 'remote work', 'confinement'];
    
        render('templates/'.$page.'.tpl.html', [
            'event_list' => make_li($events)
        ]);
    break;
    default;
        render('templates/home.tpl.html');
    break;
}


function make_li($items){
    $html = '<ul><li>';
    $html .= implode('</li><li>', $items);
    $html .= '</li></ul>';
    return $html;
}

function render($template, $params = []){
    $content = file_get_contents(__DIR__.'/'.$template);
    $site_title = "Mon titre de site";
    $subtitle = "Mon sous-titre de site";
    $default_params = [
        'site_title'=> $site_title,
        'subtitle'=> $subtitle,
    ];

    $params = array_merge($default_params, $params);
    foreach ($params as $key => $param) {
        $content = str_replace('{'.$key.'}', $param, $content);
    }

    echo $content;
}
