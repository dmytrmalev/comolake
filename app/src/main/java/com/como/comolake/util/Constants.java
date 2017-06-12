package com.como.comolake.util;

import com.como.comolake.R;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by prolance on 6/2/16.
 */
public class Constants {

    public static int ENGLISH = 0;
    public static int ITALIAN = 1;
    public static int GERMAN = 2;
    public static int FRENCH = 3;
    public static int RUSSIAN = 4;

    public static int PARTICULARITY = 0;
    public static int COMOCITY = 1;
    public static int TOWN = 2;
    public static int VILLA = 3;

    public static int VIDEO_CATEGORY = 0;
    public static int VIDEO_LIST = 1;

    public static int INTRO_LANGUAGE_SELECTORS[] = {R.id.layout_flg_en, R.id.layout_flg_it, R.id.layout_flg_de, R.id.layout_flg_fr, R.id.layout_flg_ru};
    public static int INTRO_FLAGS[] = {R.drawable.intro_flg_en, R.drawable.intro_flg_it, R.drawable.intro_flg_ge, R.drawable.intro_flg_fr, R.drawable.intro_flg_ru};
    public static int INTRO_AUDIOS[] = {R.raw.intro_en, R.raw.intro_it, R.raw.intro_de, R.raw.intro_fr, R.raw.intro_ru};
    public static String INTRO_EXIT[] = {"EXIT", "ESCI", "AUSFAHRT", "SORTIE", "ВЫХОД"};
    public static String INTRO_LANGSEL[] = {"SELECT LANGUAGES", "SELEZIONA LINGUA", "SPRACHE AUSWAHLEN", "SELECTIONNER UNE LANGUE", "ВЫБОР ЯЗЫКОВ"};
    public static String INTRO_GO[] = {"GO", "VAI", "GEHEN", "DEMARRER", "ВПЕРЕД"};

    public static String HOME_MAP[] = {"MAP", "MAPPA", "KARTE", "PLAN", "КАРТА"};
    public static String HOME_VIDEO[] = {"VIDEO", "VIDEO", "VIDÉO", "VIDEO", "ВИДЕО"};
    public static String HOME_DESCRIPTION_HEADER_NORMAL[] = {"Welcome in ", "Benvenuto in ", "Willkommen in der ", "Bienvenue à ", "Вас приветствует путеводитель "};
    public static String HOME_DESCRIPTION_HEADER_BOLD[] = {"EXPO COMO CITY GUIDE", "EXPO COMO CITY GUIDA", "EXPO COMO CITY Führung", "COMO CITY GUIDE", "EXPO ГОРОД КОМО"};
    public static String HOME_DESCRIPTION_CONTENT[] = {
            "The application is the first and only video guide entirely dedicated to Como city. Ease of use and high-level design make it a real interactive travel guide pocket. The application provides access in seconds to high quality video related to the peculiarities of the city of Como as monuments, churches, squares and historic buildings.",
            "L’applicazione è la prima e unica videoguida interamente dedicata al territorio comasco. Semplicità di utilizzo e design di alto livello la rendono una vera e propria guida turistica interattiva tascabile. L’applicazione consente di accedere in pochi secondi a video di alta qualità relativi alle particolarità della città di Como come monumenti, chiese, piazze o edifici storici.",
            "Die Anwendung ist die erste und einzige Video Führung welche dem Comer Gebiet gewidmet ist. Einfache Bedienung und High-Level-Design machen es zu einem echten interaktiven Taschen-Reiseführer. Die Anwendung bietet in wenigen Sekunden Zugriff zu hochwertigen Videos betreffend den Attraktionen der Stadt Como wie Denkmale, Kirchen, Märkte oder historische Gebäude.",
            "L'application est le premier et le seul guide video dédié à la région de Côme. la facilité d'utilisation et la conception de haut niveau en font un véritable guide touristique poche interactive. L'application permet d'accéder à la vidéo de haute qualité en quelques secondes par rapport aux particularités de la ville de Côme monuments, églises, places et bâtiments historiques.",
            "Данное приложение – это первый и единственный видеогид, полностью посвященный области Комо. Простота использования и дизайн высокого уровня делают его самым настоящим интерактивным карманным путеводителем. Приложение всего за несколько секунд обеспечивает доступ к видео высокого качества, которое познакомит Вас с достопримечательностями города Комо, расскажет о его памятниках, церквях, площадях и исторических зданиях."
    };
    public static String HOME_DESCRIPTION_FOOTER[] = {
            "Visit the City of Como has never been so easy and fun! Good use!",
            "Visitare la Città di Como non è mai stato così semplice e divertente! Buon utilizzo!",
            "Besuchen Sie die Stadt Como hat noch nie so einfach und macht Spaß! Gute Nutzung!",
            "Visitez la ville de Côme n'a jamais été aussi facile et amusant! Bonne utilisation!",
            "Посетить город Комо стало как никогда легко и увлекательно! Приятного использования!"
    };

    public static LatLng MAP_POSITIONS[][] = {
            {
                    new LatLng(45.803089, 9.319153),
                    new LatLng(45.778617, 9.133581),
                    new LatLng(45.757638, 9.018661),
                    new LatLng(46.017198, 9.219215),
                    new LatLng(45.706856, 9.114732),
                    new LatLng(45.717701, 8.952181),
                    new LatLng(45.975864, 9.038937),
                    new LatLng(45.814089, 9.070978),
                    new LatLng(45.977391, 9.253512),
                    new LatLng(46.0160486, 9.2571676),
                    new LatLng(46.154745, 9.408073),
                    new LatLng(45.76824143, 9.27022934),
                    new LatLng(45.95520748, 9.18748856),
            },
            {
                    new LatLng(45.8117478, 9.0830051),
                    new LatLng(45.8110188, 9.0810297),
                    new LatLng(45.819231, 9.066054),
            },
            {
                    new LatLng(46.147338, 9.305494),
                    new LatLng(45.9646755, 9.1765082),
                    new LatLng(46.1312923, 9.3671931),
                    new LatLng(45.852637, 9.397745),
                    new LatLng(45.9782492, 9.2588491),
                    new LatLng(45.8403609, 9.0753065),
                    new LatLng(46.0217068, 9.2388285),
                    new LatLng(46.0090947, 9.285115),
                    new LatLng(45.9848619, 9.2288189),
            },
            {
                    new LatLng(45.96727, 9.184042),
                    new LatLng(45.98196, 9.21466),
                    new LatLng(46.021295, 9.033073),
                    new LatLng(45.8568633, 9.1380633),
                    new LatLng(45.838967, 9.074682),
                    new LatLng(45.851321, 9.089954),
                    new LatLng(45.978921, 9.253073),
                    new LatLng(45.879918, 9.138538),
                    new LatLng(45.859944, 9.097674),
                    new LatLng(45.965077, 9.202497),
                    new LatLng(45.965191, 9.202339),
                    new LatLng(46.040279, 9.250971),
                    new LatLng(45.845990, 9.086284),
                    new LatLng(45.834207, 9.095687),
                    new LatLng(46.00919, 9.28563),
                    new LatLng(45.988751, 9.260917),
                    new LatLng(45.848255, 9.107542),
                    new LatLng(45.984478, 9.228288),
                    new LatLng(45.986499, 9.231496),
                    new LatLng(45.985922, 9.230536),
            }
    };

    public static int MAP_MARKERS[] = {R.drawable.map_marker_red, R.drawable.map_marker_orange, R.drawable.map_marker_green, R.drawable.map_marker_blue};
    public static String MAP_VIDEO[] = {"VIDEO", "VAL AL VIDEO", "VIDEO", "VIDEO", "ВИДЕО"};
    public static String MAP_NAVIGATION[] = {"NAVIGATION", "AVVIA NAVIGAZIONE", "FEUILLETER", "NAVIGIEREN", "НАВИГАЦИЯ"};
    public static int MAP_CATEGORY_COLORIMAGE[] = {R.drawable.map_btn_particular, R.drawable.map_btn_city, R.drawable.map_btn_town, R.drawable.map_btn_villa};
    public static int MAP_CATEGORY_GRAYSCALEIMAGE[] = {R.drawable.map_btn_particular_grayscale, R.drawable.map_btn_city_grayscale, R.drawable.map_btn_town_grayscale, R.drawable.map_btn_villa_grayscale};
    public static int MAP_CATEGORY_IMAGES[] = {R.id.img_particularity, R.id.img_comocity, R.id.img_town, R.id.img_villa};
    public static int MAP_CATEGORY_LAYOUTS[] = {R.id.layout_particular, R.id.layout_comocity, R.id.layout_town, R.id.layout_villa};
    public static int MAP_CATEGORY_RGBCOLOR[] = {R.color.colorMapParticularRGB, R.color.colorMapComocityRGB, R.color.colorMapTownRGB, R.color.colorMapVillaRGB};
    public static int MAP_CATEGORY_GRAYSCALECOLOR[] = {R.color.colorMapParticularGrayscale, R.color.colorMapComocityGrayscale, R.color.colorMapTownGrayscale, R.color.colorMapVillaGrayscale};
    public static int MAP_THUMBNAIL_IMAGES[][] = {
            {
                    R.drawable.thumbnail_golf_club_lecco, R.drawable.thumbnail_cricolco_golf_villa_d_este, R.drawable.thumbnail_golf_club_monticello, R.drawable.thumbnail_menaggio_e_cadenbbia_golf_club,
                    R.drawable.thumbnail_golf_club_carimate, R.drawable.thumbnail_la_pinetina_golf_club, R.drawable.thumbnail_golf_club_lanzo, R.drawable.thumbnail_aeroclub,
                    R.drawable.thumbnail_ristorante_silvio_da_pesca, R.drawable.thumbnail_navigare_sul_lago_di_como, R.drawable.thumbnail_pian_di_spagna_e_alto_lago, R.drawable.thumbnail_briazana,
                    R.drawable.thumbnail_la_storia_della_navigazione
            },
            {
                    R.drawable.thumbnail_piazza_duomo, R.drawable.thumbnail_via_vittani, R.drawable.thumbnail_villa_olmo
            },
            {
                    R.drawable.thumbnail_gravedona, R.drawable.thumbnail_isola_comacina_case_per_artisti, R.drawable.thumbnail_colico_e_piona, R.drawable.thumbnail_lecco,
                    R.drawable.thumbnail_bellagio, R.drawable.thumbnail_cernobbio, R.drawable.thumbnail_menaggio, R.drawable.thumbnail_varenna, R.drawable.thumbnail_tremezzina
            },
            {
                    R.drawable.thumbnail_villa_cassinella, R.drawable.thumbnail_villa_sola, R.drawable.thumbnail_villa_fogazzaro, R.drawable.thumbnail_villa_pliniana,
                    R.drawable.thumbnail_villa_erba, R.drawable.thumbnail_villa_fontanelle, R.drawable.thumbnail_villa_melzi, R.drawable.thumbnail_villa_oleandra,
                    R.drawable.thumbnail_villa_passalacqua, R.drawable.thumbnail_villa_balbianello_esterno, R.drawable.thumbnail_villa_balbianello_interno, R.drawable.thumbnail_villa_la_gaeta,
                    R.drawable.thumbnail_villa_pizzo, R.drawable.thumbnail_villa_troubletzkoy, R.drawable.thumbnail_villa_monastero, R.drawable.thumbnail_grand_hotel_villa_serbelloni,
                    R.drawable.thumbnail_castavida, R.drawable.thumbnail_grand_hotel_tremezzo, R.drawable.thumbnail_villa_carlotta_esterno, R.drawable.thumbnail_villa_carlotta_interno
            }
    };

    public static String MAIN_CATEGORIES[][] = {
            {"PARTICULARITY", "PARTICOLARITÀ", "BESONDERHEIT", "PARTICULARITÉ", "ОСОБЕННОСТЬ"},
            {"COMO CITY", "COME CITTÀ", "COMO STADT", "LA VILLE DE CÔME", "ГОРОД КОМО"},
            {"TOWN", "PAESI", "LANDERN", "VILLAGES", "Страны"},
            {"VILLA", "VILLE", "VILLA", "LES VILLAS", "Виллы"}
    };

    public static String CAT_VIDEO_TITLES[][][] = {
            {
                    {"GOLF", "GOLF CLUB LECCO", "GOLF", "GOLF", "ГОЛЬФ"},
                    {"GOLF", "CIRCOLO GOLF VILLA D'ESTE", "GOLF", "GOLF", "ГОЛЬФ"},
                    {"GOLF", "GOLF CLUB MONTICELLO", "GOLF", "GOLF", "ГОЛЬФ"},
                    {"GOLF", "MENAGGIO E CADENABBIA GOLF CLUB", "GOLF", "GOLF", "ГОЛЬФ"},
                    {"GOLF", "GOLF CLUB CARIMATE", "GOLF", "GOLF", "ГОЛЬФ"},
                    {"GOLF", "LA PINETINA GOLF CLUB", "GOLF", "GOLF", "ГОЛЬФ"},
                    {"GOLF", "GOLF CLUB LANZO", "GOLF", "GOLF", "ГОЛЬФ"},
                    {"FLYING CLUB COMO", "AEROCLUB", "AEROCLUB", "AERO CLUB", "АЭРОКЛУБ КОМО"},
                    {"FISHING ON LAKE COMO", "RISTONRANTE SILVIO DA PESCA", "FISCHEREI AM COMER SEE", "LA PECHE SUR LE LAC DE COME", "РЫБАЛКА НА ОЗЕРЕ КОМОI"},
                    {"SAILING ON LAKE COMO", "NAVIGARE SUL LAGO DI COMO", "SCHIFFFAHRT AM COMER SEE", "NAVIGUER SUR LE LAC DE COME", "НАВИГАЦИЯ НА ОЗЕРЕ КОМО"},
                    {"PIAN OF SPAGNA AND UPPER LAKE", "PIAN DI SPAGNA E ALTO LAGO", "PIAN DI SPAGNA SPORT OBERER TEIL DES SEES", "PIAN DI SPAGNA SPORT HAUT LAC", "Пьян ди Спанья Спорт Верхнего Озера"},
                    {"BRIANZA", "BRIANZA", "BRIANZA", "BRIANZA", "БРИАНЦА"},
                    {"THE HISTORY OF NAVIGATION", "LA STORIA DELLA NAVIGAZIONE", "DIE GESCHICHTE DER SCHIFFFAHRT", "HISTORIE DA LA NAVIGATION", "ИСТОРИЯ НАВИГАЦИИ"}
            },
            {
                    {"THE DUOMO SQUARE", "PIZZA DUOMO", "DOM-PLATZ", "PLADE DU DOME", "Площадь Дуомо"},
                    {"VIA VITTANI", "VIA VITTANI", "VIA VITTANI", "VIA VITTANI", "Виа Виттани"},
                    {"VILLA OLMO", "VILLA OLMO", "VILLA OLMO", "VILLA OLMO", "Вилла Ольмо"}
            },
            {
                    {"GRAVEDONA", "GRAVEDONA", "GRAVEDONA", "GRAVEDONA", "ГРАВЕДОНА"},
                    {"HOUSE FOR ARTISTS", "ISOLA COMACINA CASE PER ARTISTI", "HAUSER FUR KUNSTLER", "MAISONS POUR ARTISTES", "ОСТРОВ КОМАЧИНА. ДОМА ХУДОЖНИКОВ"},
                    {"COLIOCO AND PIONA", "COLIOCO E PIONA", "COLICO UND PIONA", "COLICO ET PIONA", "КОЛИКО И ПИОНА"},
                    {"LECCO", "LECCO", "LECCO", "LECCO", "ЛЕККО"},
                    {"BELLAGIO", "BELLAGIO", "BELLAGIO", "BELLAGIO", "БЕЛЛАДЖИО"},
                    {"CERNOBBIO", "CERNOBBIO", "CERNOBBIO", "CERNOBBIO", "Черноббио"},
                    {"MENAGGIO", "MENAGGIO", "MENAGGIO", "MENAGGIO", "МЕНАДЖО"},
                    {"VARENNA", "VARENNA", "VARENNA", "VARENNA", "ВАРЕННА"},
                    {"TREMEZZINA", "TREMEZZINA", "TREMEZZINA", "TREMEZZINA", "ТРЕМЕЦЦИНА"}
            },
            {
                    {"VILLA CASSINELLA", "VILLA CASSINELLA", "VILLA CASSINELLA", "VILLA CASSINELLA", "ВИЛЛА КАССИНЕЛЛА"},
                    {"VILLA SOLA", "VILLA SOLA", "VILLA SOLA LA QUIETE", "VILLA SOLA LA QUIETE", "ВИЛЛА СОЛА ЛА КВИЕТЕ"},
                    {"VILLA FOGAZZARO", "VILLA FOGAZZARO", "VILLA FOGAZZARO", "VILLA FOGAZZARO", "Вилла Fogazzaro"},
                    {"VILLA PLINIANA", "VILLA PLINIANA", "VILLA PLINIANA", "VILLA PLINIANA", "Вилла Плиниана"},
                    {"VILLA ERBA", "VILLA ERBA", "VILLA ERBA", "VILLA ERBA", "Вилла Эрба"},
                    {"VILLA FONTANELLE", "VILLA FONTANELLE", "VILLA FONTANELLE", "VILLA FONTANELLE", "Вилла Фонтанелле"},
                    {"VILLA MELZI", "VILLA MELZI", "VILLA MELZI", "VILLA MELZI", "Вилла Мельци"},
                    {"VILLA OLEANDRA", "VILLA OLEANDRA", "VILLA OLEANDRA", "VILLA OLEANDRA", "Вилла Олеандра"},
                    {"VILLA PASSALACQUA", "VILLA PASSALACQUA", "VILLA PASSALACQUA", "VILLA PASSALACQUA", "Вилла Пассалакуа"},
                    {"VILLA BALBIANELLO EXTERNAL", "VILLA BALBIANELLO ESTERNO", "VILLA BALBIANELLO AUßEN", "VILLA BALBIANELLO EXTERIEUR", "Вилла Бальбьянелло Снаружи"},
                    {"VILLA BALBINANELLO INTERNAL", "VILLA BALBINANELLO INTERNO", "VILLA BALBINANELLO Innen", "VILLA BALBINANELLO Intérieur", "Вилла Бальбьянелло Интерьер"},
                    {"VILLA GAETA", "VILLA GAETA", "VILLA GAETA", "VILLA GAETA", "Вилла Ла Гаэта"},
                    {"VILLA PIZZO", "VILLA PIZZO", "VILLA PIZZO", "VILLA PIZZO", "Вилла Пиццо"},
                    {"VILLA TROUBETZKOY", "VILLA TROUBETZKOY", "VILLA TROUBETZKOY", "VILLA TROUBETZKOY", "Вилла Трубецкого"},
                    {"VILLA MONASTERO", "VILLA MONASTERO", "VILLA MONASTERO", "VILLA MONASTERO", "ВИЛЛА МОНАСТЕРО"},
                    {"GRAND HOTEL VILLA SERBELLONI", "GRAND HOTEL VILLA SERBELLONI", "GRAND HOTEL VILLA SERBELLONI", "GRAND HOTEL VILLA SERBELLONI", "Гранд Отель Вилла Сербеллони"},
                    {"CASTADIVA", "CASTADIVA", "CASTADIVA", "CASTADIVA", "КастаДива"},
                    {"GRAND HOTEL TREMEZZO", "GRAND HOTEL TREMEZZO", "GRAND HOTEL TREMEZZO", "GRAND HOTEL TREMEZZO", "ГРАНД ОТЕЛЬ ТРЕМЕЦЦО"},
                    {"VILLA CARLOTTA EXTERNAL", "VILLA CARLOTTA ESTERNO", "VILLA CARLOTTA AUßEN", "VILLA CARLOTTA EXTERIEUR", "ВИЛЛА КАРЛОТТА ЭКСТЕРЬЕР"},
                    {"VILLA CARLOTTA INTERNAL", "VILLA CARLOTTA INTERNO", "VILLA CARLOTTA Innen", "VILLA CARLOTTA Intérieur", "Вилла Карлотта Интерьер"},
            }
    };
    
    public static int LIST_COVER_IMAGES[][] = {
            {
                    R.drawable.cover_golf_club_lecco, R.drawable.cover_cricolo_golf_villa_d_este, R.drawable.cover_golf_club_monticello, R.drawable.cover_menaggio_e_cadenabbia_golf_club,
                    R.drawable.cover_golf_club_carimate, R.drawable.cover_la_pinetina_golf_club, R.drawable.cover_golf_club_lanzo, R.drawable.cover_aeroclub,
                    R.drawable.cover_ristorante_silvio_da_pesca, R.drawable.cover_navigare_sul_lago_di_como, R.drawable.cover_pian_di_spagna_alto_lago, R.drawable.cover_brianza,
                    R.drawable.cover_la_storia_della_navigazione
            },
            {
                    R.drawable.cover_piazza_duomo, R.drawable.cover_via_vittani, R.drawable.cover_villa_olmo
            },
            {
                    R.drawable.cover_gravedona, R.drawable.cover_isola_comacina_case_per_artisti, R.drawable.cover_colico_e_piona, R.drawable.cover_lecco,
                    R.drawable.cover_bellagio, R.drawable.cover_cernobbio, R.drawable.cover_menaggio, R.drawable.cover_varenna, R.drawable.cover_tremezzina
            },
            {
                    R.drawable.cover_villa_cassinella, R.drawable.cover_villa_sola, R.drawable.cover_villa_fogazzaro, R.drawable.cover_villa_pliniana,
                    R.drawable.cover_villa_erba, R.drawable.cover_villa_fontanelle, R.drawable.cover_villa_melzi, R.drawable.cover_villa_oleandra,
                    R.drawable.cover_villa_passalacqua, R.drawable.cover_villa_balbianello_esterno, R.drawable.cover_villa_balbianello_interno, R.drawable.cover_villa_la_gaeta,
                    R.drawable.cover_villa_pizzo, R.drawable.cover_villa_troubletzkoy, R.drawable.cover_villa_monastero, R.drawable.cover_grand_hotel_villa_serbellioni,
                    R.drawable.cover_castavida, R.drawable.cover_grand_hotel_tremezzo, R.drawable.cover_villa_caroltta_esterno, R.drawable.cover_villa_carlotta_interno
            }
    };

//    public static int LIST_VIDEOS[][] = {
//            {
//                    R.raw.golf_club_lecco, R.raw.cricolo_golf_villa_d_este, R.raw.golf_club_monticello, R.raw.menaggio_e_cadenabbia_golf_club, R.raw.golf_club_carimate,
//                    R.raw.la_pinetina_golf_club, R.raw.golf_club_lanzo, R.raw.aeroclub, 0, R.raw.navigare_sul_lago_di_como, R.raw.pian_di_spagna_e_alto_lago,
//                    R.raw.brianaza, R.raw.la_storia_della_navigazione
//            },
//            {
//                    R.raw.piazza_duomo, R.raw.via_vittani, R.raw.villa_olmo
//            },
//            {
//                    R.raw.gravedona, R.raw.isola_comacina_case_per_artisti, R.raw.colico_e_piona, R.raw.lecco, R.raw.bellagio, R.raw.cernobbio, R.raw.menaggio, R.raw.vareena, R.raw.tremezzina
//            },
//            {
//                    R.raw.villa_cassinella, R.raw.villa_sola, R.raw.villa_fogazzaro, R.raw.villa_pliniana, R.raw.villa_erba, R.raw.villa_fontanelle, R.raw.villa_melzi, R.raw.villa_oleandra,
//                    R.raw.villa_passalacqua, R.raw.villa_balbianello_esterno, R.raw.villa_balbianello_interno, R.raw.villa_la_gaeta, R.raw.villa_pizzo, R.raw.villa_troubletzkoy, R.raw.villa_monstero,
//                    R.raw.grand_hotel_villa_serbelloni, R.raw.castadiva, R.raw.grand_hotel_tremezzo,  R.raw.villa_carlotta_esterno, R.raw.villa_carlotta_interno
//            }
//    };
//
//    public static int LIST_AUDIOS[][][] = {
//            {
//                    { R.raw.golf_club_lecco_en, R.raw.golf_club_lecco_it, R.raw.golf_club_lecco_de, R.raw.golf_club_lecco_fr, R.raw.golf_club_lecco_ru },
//                    { R.raw.cricolo_golf_villa_d_este_en, R.raw.cricolo_golf_villa_d_este_it, R.raw.cricolo_golf_villa_d_este_de, R.raw.cricolo_golf_villa_d_este_fr, R.raw.cricolo_golf_villa_d_este_ru },
//                    { R.raw.golf_club_monticello_en, R.raw.golf_club_monticello_it, R.raw.golf_club_monticello_de, R.raw.golf_club_monticello_fr, R.raw.golf_club_monticello_ru },
//                    { R.raw.menaggio_e_cadenabbia_golf_club_en, R.raw.menaggio_e_cadenabbia_golf_club_it, R.raw.menaggio_e_cadenabbia_golf_club_de, R.raw.menaggio_e_cadenabbia_golf_club_fr, R.raw.menaggio_e_cadenabbia_golf_club_ru },
//                    { R.raw.golf_club_carimate_en, R.raw.golf_club_carimate_it, R.raw.golf_club_carimate_de, R.raw.golf_club_carimate_fr, R.raw.golf_club_carimate_ru },
//                    { R.raw.la_pinetina_golf_club_en, R.raw.la_pinetina_golf_club_it, R.raw.la_pinetina_golf_club_de, R.raw.la_pinetina_golf_club_fr, R.raw.la_pinetina_golf_club_ru },
//                    { R.raw.golf_club_lanzo_en, R.raw.golf_club_lanzo_it, R.raw.golf_club_lanzo_de, R.raw.golf_club_lanzo_fr, R.raw.golf_club_lanzo_ru },
//                    { R.raw.aeroclub_en, R.raw.aeroclub_it, R.raw.aeroclub_de, R.raw.aeroclub_fr, R.raw.aeroclub_ru },
//                    { R.raw.ristonrante_silvio_da_pesca_en, R.raw.ristonrante_silvio_da_pesca_it, R.raw.ristonrante_silvio_da_pesca_de, R.raw.ristonrante_silvio_da_pesca_fr, R.raw.ristonrante_silvio_da_pesca_ru },
//                    { R.raw.navigare_sul_lago_di_como_en, R.raw.navigare_sul_lago_di_como_it, R.raw.navigare_sul_lago_di_como_de, R.raw.navigare_sul_lago_di_como_fr, R.raw.navigare_sul_lago_di_como_ru },
//                    { R.raw.pian_di_spagna_e_alto_lago_en, R.raw.pian_di_spagna_e_alto_lago_it, R.raw.pian_di_spagna_e_alto_lago_de, R.raw.pian_di_spagna_e_alto_lago_fr, R.raw.pian_di_spagna_e_alto_lago_ru },
//                    { R.raw.brianza_en, R.raw.brianza_it, R.raw.brianza_de, R.raw.brianza_fr, R.raw.brianza_ru },
//                    { R.raw.la_storia_della_navigazione_en, R.raw.la_storia_della_navigazione_it, R.raw.la_storia_della_navigazione_de, R.raw.la_storia_della_navigazione_fr, R.raw.la_storia_della_navigazione_ru },
//            },
//            {
//                    { R.raw.piazza_duomo_en, R.raw.piazza_duomo_it, R.raw.piazza_duomo_de, R.raw.piazza_duomo_fr, R.raw.piazza_duomo_ru },
//                    { R.raw.via_vittani_en, R.raw.via_vittani_it, R.raw.via_vittani_de, R.raw.via_vittani_fr, R.raw.via_vittani_ru },
//                    { R.raw.villa_olmo_en, R.raw.villa_olmo_it, R.raw.villa_olmo_de, R.raw.villa_olmo_fr, R.raw.villa_olmo_ru },
//            },
//            {
//                    { R.raw.gravedona_en, R.raw.gravedona_it, R.raw.gravedona_de, R.raw.gravedona_fr, R.raw.gravedona_ru },
//                    { R.raw.isola_comacina_case_per_artisti_en, R.raw.isola_comacina_case_per_artisti_it, R.raw.isola_comacina_case_per_artisti_de, R.raw.isola_comacina_case_per_artisti_fr, R.raw.isola_comacina_case_per_artisti_ru }
//                    { R.raw.colico_e_piona_en, R.raw.colico_e_piona_it, R.raw.colico_e_piona_de, R.raw.colico_e_piona_fr, R.raw.colico_e_piona_ru },
//                    { R.raw.lecco_en, R.raw.lecco_it, R.raw.lecco_de, R.raw.lecco_fr, R.raw.lecco_ru },
//                    { R.raw.bellagio_en, R.raw.bellagio_it, R.raw.bellagio_de, R.raw.bellagio_fr, R.raw.bellagio_ru },
//                    { R.raw.cernobbio_en, R.raw.cernobbio_it, R.raw.cernobbio_de, R.raw.cernobbio_fr, R.raw.cernobbio_ru },
//                    { R.raw.menaggio_en, R.raw.menaggio_it, R.raw.menaggio_de, R.raw.menaggio_fr, R.raw.menaggio_ru },
//                    { R.raw.varenna_en, R.raw.varenna_it, R.raw.varenna_de, R.raw.varenna_fr, R.raw.varenna_ru },
//                    { R.raw.tremezzina_en, R.raw.tremezzina_it, R.raw.tremezzina_de, R.raw.tremezzina_fr, R.raw.tremezzina_ru }
//            },
//            {
//                    { R.raw.villa_cassinella_en, R.raw.villa_cassinella_it, R.raw.villa_cassinella_de, R.raw.villa_cassinella_fr, R.raw.villa_cassinella_ru },
//                    { R.raw.villa_sola_en, R.raw.villa_sola_it, R.raw.villa_sola_de, R.raw.villa_sola_fr, R.raw.villa_sola_ru },
//                    { R.raw.villa_fogazzaro_en, R.raw.villa_fogazzaro_it, R.raw.villa_fogazzaro_de, R.raw.villa_fogazzaro_fr, R.raw.villa_fogazzaro_ru },
//                    { R.raw.villa_pliniana_en, R.raw.villa_pliniana_it, R.raw.villa_pliniana_de, R.raw.villa_pliniana_fr, R.raw.villa_pliniana_ru },
//                    { R.raw.villa_erba_en, R.raw.villa_erba_it, R.raw.villa_erba_de, R.raw.villa_erba_fr, R.raw.villa_erba_ru },
//                    { R.raw.villa_fontanelle_en, R.raw.villa_fontanelle_it, R.raw.villa_fontanelle_de, R.raw.villa_fontanelle_fr, R.raw.villa_fontanelle_ru },
//                    { R.raw.villa_melzi_en, R.raw.villa_melzi_it, R.raw.villa_melzi_de, R.raw.villa_melzi_fr, R.raw.villa_melzi_ru },
//                    { R.raw.villa_oleandra_en, R.raw.villa_oleandra_it, R.raw.villa_oleandra_de, R.raw.villa_oleandra_fr, R.raw.villa_oleandra_ru },
//                    { R.raw.villa_passalacqua_en, R.raw.villa_passalacqua_it, R.raw.villa_passalacqua_de, R.raw.villa_passalacqua_fr, R.raw.villa_passalacqua_ru },
//                    { R.raw.villa_balbianello_esterno_en, R.raw.villa_balbianello_esterno_it, R.raw.villa_balbianello_esterno_de, R.raw.villa_balbianello_esterno_fr, R.raw.villa_balbianello_esterno_ru },
//                    { R.raw.villa_balbianello_interno_en, R.raw.villa_balbianello_interno_it, R.raw.villa_balbianello_interno_de, R.raw.villa_balbianello_interno_fr, R.raw.villa_balbianello_interno_ru },
//                    { R.raw.villa_la_gaeta_en, R.raw.villa_la_gaeta_it, R.raw.villa_la_gaeta_de, R.raw.villa_la_gaeta_fr, R.raw.villa_la_gaeta_ru },
//                    { R.raw.villa_pizzo_en, R.raw.villa_pizzo_it, R.raw.villa_pizzo_de, R.raw.villa_pizzo_fr, R.raw.villa_pizzo_ru },
//                    { R.raw.villa_troubetzkoy_en, R.raw.villa_troubetzkoy_it, R.raw.villa_troubetzkoy_de, R.raw.villa_troubetzkoy_fr, R.raw.villa_troubetzkoy_ru },
//                    { R.raw.villa_monastero_en, R.raw.villa_monastero_it, R.raw.villa_monastero_de, R.raw.villa_monastero_it, R.raw.villa_monastero_ru },
//                    { R.raw.grand_hotel_villa_serbelloni_en, R.raw.grand_hotel_villa_serbelloni_it, R.raw.grand_hotel_villa_serbelloni_de, R.raw.grand_hotel_villa_serbelloni_fr, R.raw.grand_hotel_villa_serbelloni_ru },
//                    { R.raw.castadiva_en, R.raw.castadiva_it, R.raw.castadiva_de, R.raw.castadiva_fr, R.raw.castadiva_ru },
//                    { R.raw.grand_hotel_tremezzo_en, R.raw.grand_hotel_tremezzo_it, R.raw.grand_hotel_tremezzo_de, R.raw.grand_hotel_tremezzo_fr, R.raw.grand_hotel_tremezzo_ru },
//                    { R.raw.villa_carlotta_esterno_en, R.raw.villa_carlotta_esterno_it, R.raw.villa_carlotta_esterno_de, R.raw.villa_carlotta_esterno_fr, R.raw.villa_carlotta_esterno_ru },
//                    { R.raw.villa_carlotta_interno_en, R.raw.villa_carlotta_interno_it, R.raw.villa_carlotta_interno_de, R.raw.villa_carlotta_interno_fr, R.raw.villa_carlotta_interno_ru }
//            }
//    };
}
