package com.example.duofinder_tfg;

import java.util.HashMap;
import java.util.Map;

public class Images {

    public Images(){
        for (int i=0;i<champs.length;i++){
            championsMap.put(champs[i],champsImages[i]);
        }
        for (int i=0;i<elos.length;i++){
            elosMap.put(elos[i], elosImages[i]);
        }
        for (int i=0;i<roles.length;i++){
            rolesMap.put(roles[i], rolesImages[i]);
        }
    }

    static final int[] champsImages = new int[]{R.drawable.aatrox, R.drawable.ahri, R.drawable.akali, R.drawable.alistar, R.drawable.amumu, R.drawable.anivia, R.drawable.annie, R.drawable.aphelios, R.drawable.ashe,
            R.drawable.aurelion_sol, R.drawable.azir, R.drawable.bard, R.drawable.blitzcrank, R.drawable.brand, R.drawable.braum, R.drawable.caitlyn, R.drawable.camille, R.drawable.cassiopeia,
            R.drawable.chogath, R.drawable.corki, R.drawable.darius, R.drawable.diana, R.drawable.drmundo, R.drawable.draven, R.drawable.ekko, R.drawable.elise, R.drawable.evelynn, R.drawable.ezreal,
            R.drawable.fiddlesticks, R.drawable.fiora, R.drawable.fizz, R.drawable.galio, R.drawable.gangplank, R.drawable.garen, R.drawable.gnar, R.drawable.gragas, R.drawable.graves, R.drawable.hecarim,
            R.drawable.heimer, R.drawable.illaoi, R.drawable.irelia, R.drawable.ivern, R.drawable.janna, R.drawable.jarvan, R.drawable.jax, R.drawable.jayce, R.drawable.jhin, R.drawable.jinx, R.drawable.kaisa,
            R.drawable.kalista, R.drawable.karma, R.drawable.karthus, R.drawable.kassadin, R.drawable.katarina, R.drawable.kayle, R.drawable.kayn, R.drawable.kennen, R.drawable.khazix, R.drawable.kindred,
            R.drawable.kled, R.drawable.kogmaw, R.drawable.leblanc, R.drawable.leesin, R.drawable.leona, R.drawable.lissandra, R.drawable.lucian, R.drawable.lulu, R.drawable.lux, R.drawable.malphite,
            R.drawable.malzahar, R.drawable.maokai, R.drawable.masteryi, R.drawable.missfortune, R.drawable.mordekaiser, R.drawable.morgana, R.drawable.nami, R.drawable.nasus, R.drawable.nautilus, R.drawable.neeko,
            R.drawable.nidalee, R.drawable.nocturne, R.drawable.nunu, R.drawable.olaf, R.drawable.orianna, R.drawable.ornn, R.drawable.pantheon, R.drawable.poppy, R.drawable.pyke, R.drawable.qiyana, R.drawable.quinn,
            R.drawable.rakan, R.drawable.rammus, R.drawable.reksai, R.drawable.renekton, R.drawable.rengar, R.drawable.riven, R.drawable.rumble, R.drawable.ryze, R.drawable.sejuani, R.drawable.senna, R.drawable.sett,
            R.drawable.shaco, R.drawable.shen, R.drawable.shyvanna, R.drawable.singed, R.drawable.sion, R.drawable.sivir, R.drawable.skarner, R.drawable.sona, R.drawable.soraka, R.drawable.swain, R.drawable.sylas,
            R.drawable.syndra, R.drawable.tahmkench, R.drawable.taliyah, R.drawable.talon, R.drawable.taric, R.drawable.teemo, R.drawable.thresh, R.drawable.tristana, R.drawable.trundle, R.drawable.tryndamere,
            R.drawable.twistedfate, R.drawable.twitch, R.drawable.udyr, R.drawable.urgot, R.drawable.varus, R.drawable.vayne, R.drawable.veigar, R.drawable.velkoz, R.drawable.vi, R.drawable.viktor, R.drawable.vladimir,
            R.drawable.volibear, R.drawable.warwick, R.drawable.wukong, R.drawable.xayah, R.drawable.xerath, R.drawable.xinzhao, R.drawable.yasuo, R.drawable.yorick, R.drawable.yuumi, R.drawable.zac, R.drawable.zed,
            R.drawable.ziggs, R.drawable.zilean, R.drawable.zoe, R.drawable.zyra};

    static final int[] elosImages = new int[]{R.drawable.iron,R.drawable.iron,R.drawable.iron,R.drawable.iron,R.drawable.bronze,R.drawable.bronze,R.drawable.bronze,R.drawable.bronze, R.drawable.silver,R.drawable.silver,R.drawable.silver,R.drawable.silver,R.drawable.gold,R.drawable.gold,
            R.drawable.gold,R.drawable.gold,R.drawable.plat,R.drawable.plat,R.drawable.plat,R.drawable.plat,R.drawable.diamond,R.drawable.diamond,R.drawable.diamond,R.drawable.diamond,R.drawable.master,R.drawable.gmaster, R.drawable.challenger};
    static final int[] rolesImages = new int[]{R.drawable.top,R.drawable.jungle,R.drawable.mid,R.drawable.adc,R.drawable.support};

    private Map<String, Integer> championsMap = new HashMap<>();
    private Map<String, Integer> elosMap = new HashMap<>();
    private Map<String, Integer> rolesMap = new HashMap<>();


    static final String[] champs = new String[]{"Aatrox","Ahri","Akali","Alistar","Amumu","Anivia","Annie","Aphelios","Ashe","Aurelion Sol","Azir","Bard","Blitzcrank","Brand","Braum","Caitlyn",
            "Camille","Cassiopeia","Cho'Gath","Corki","Darius","Diana","Dr. Mundo","Draven","Ekko","Elise","Evelynn","Ezreal","Fiddlesticks","Fiora","Fizz","Galio","Gangplank","Garen",
            "Gnar","Gragas","Graves","Hecarim","Heimerdinger","Illaoi","Irelia","Ivern","Janna","Jarvan IV","Jax","Jayce","Jhin","Jinx","Kai'Sa","Kalista","Karma","Karthus","Kassadin",
            "Katarina","Kayle","Kayn","Kennen","Kha'Zix","Kindred","Kled","Kog'Maw","LeBlanc","Lee Sin","Leona","Lissandra","Lucian","Lulu","Lux","Malphite","Malzahar","Maokai","Master Yi",
            "Miss Fortune","Mordekaiser","Morgana","Nami","Nasus","Nautilus","Neeko","Nidalee","Nocturne","Nunu and Willump","Olaf","Orianna","Ornn","Pantheon","Poppy","Pyke","Qiyana",
            "Quinn","Rakan","Rammus","Rek'Sai","Renekton","Rengar","Riven","Rumble","Ryze","Sejuani","Senna","Sett","Shaco","Shen","Shyvana","Singed","Sion","Sivir","Skarner","Sona",
            "Soraka","Swain","Sylas","Syndra","Tahm Kench","Taliyah","Talon","Taric","Teemo","Thresh","Tristana","Trundle","Tryndamere","Twisted Fate","Twitch","Udyr","Urgot","Varus",
            "Vayne","Veigar","Vel'Koz","Vi","Viktor","Vladimir","Volibear","Warwick","Wukong","Xayah","Xerath","Xin Zhao","Yasuo","Yorick","Yuumi","Zac","Zed","Ziggs","Zilean","Zoe","Zyra"};

    static final String[] servers = new String[]{"BR","EUNE","EUW","LAN","LAS","NA","OCE","RU","TR","JP","KR","CN","TW","SAM","VN","TH","PH","MENA"};
    static final String[] roles = new String[]{"TOP","JUNGLE","MID","ADC","SUPPORT"};
    static final String[] elos = new String[]{"Iron IV","Iron III","Iron II","Iron I","Bronze IV","Bronze III","Bronze II","Bronze I","Silver IV","Silver III","Silver II","Silver I","Gold IV","Gold III","Gold II","Gold I","Platinum IV","Platinum III","Platinum II","Platinum I","Diamond VI","Diamond III","Diamond II","Diamond I","Master","Grand Master","Challenger"};

    public int getChampImageId(String champ){
        int id = championsMap.get(champ);
        return id;
    }

    public int getRoleImageId(String role){
        int id = rolesMap.get(role);
        return id;
    }

    public int getEloImageId(String elo){
        int id = elosMap.get(elo);
        return id;
    }
}
