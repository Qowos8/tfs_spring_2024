package com.example.homework_2

// Class Emoji with Name and Code as String
data class EmojiNCS(
    val name: String,
    val code: String
) {
    fun getCodeString() = String(Character.toChars(code.toInt(16)))
}

val emojiSetNCS = listOf(

// Smileys & Emotion
    EmojiNCS("grinning", "0x1f600"),
    EmojiNCS("smiley", "1f603"),
    EmojiNCS("big_smile", "1f604"),
    EmojiNCS("grinning_face_with_smiling_eyes", "1f601"),
    EmojiNCS("laughing", "1f606"),
    EmojiNCS("sweat_smile", "1f605"),
    EmojiNCS("rolling_on_the_floor_laughing", "1f923"),
    EmojiNCS("joy", "1f602"),
    EmojiNCS("smile", "1f642"),
    EmojiNCS("upside_down", "1f643"),
    EmojiNCS("wink", "1f609"),
    EmojiNCS("blush", "1f60a"),
    EmojiNCS("innocent", "1f607"),
    EmojiNCS("heart_eyes", "1f60d"),
    EmojiNCS("heart_kiss", "1f618"),
    EmojiNCS("kiss", "1f617"),
    EmojiNCS("smiling_face", "263a"),
    EmojiNCS("kiss_with_blush", "1f61a"),
    EmojiNCS("kiss_smiling_eyes", "1f619"),
    EmojiNCS("yum", "1f60b"),
    EmojiNCS("stuck_out_tongue", "1f61b"),
    EmojiNCS("stuck_out_tongue_wink", "1f61c"),
    EmojiNCS("stuck_out_tongue_closed_eyes", "1f61d"),
    EmojiNCS("money_face", "1f911"),
    EmojiNCS("hug", "1f917"),
    EmojiNCS("thinking", "1f914"),
    EmojiNCS("silence", "1f910"),
    EmojiNCS("neutral", "1f610"),
    EmojiNCS("expressionless", "1f611"),
    EmojiNCS("speechless", "1f636"),
    EmojiNCS("smirk", "1f60f"),
    EmojiNCS("unamused", "1f612"),
    EmojiNCS("rolling_eyes", "1f644"),
    EmojiNCS("grimacing", "1f62c"),
    EmojiNCS("lying", "1f925"),
    EmojiNCS("relieved", "1f60c"),
    EmojiNCS("pensive", "1f614"),
    EmojiNCS("sleepy", "1f62a"),
    EmojiNCS("drooling", "1f924"),
    EmojiNCS("sleeping", "1f634"),
    EmojiNCS("cant_talk", "1f637"),
    EmojiNCS("sick", "1f912"),
    EmojiNCS("hurt", "1f915"),
    EmojiNCS("nauseated", "1f922"),
    EmojiNCS("sneezing", "1f927"),
    EmojiNCS("dizzy", "1f635"),
    EmojiNCS("cowboy", "1f920"),
    EmojiNCS("sunglasses", "1f60e"),
    EmojiNCS("nerd", "1f913"),
    EmojiNCS("oh_no", "1f615"),
    EmojiNCS("worried", "1f61f"),
    EmojiNCS("frown", "1f641"),
    EmojiNCS("sad", "2639"),
    EmojiNCS("open_mouth", "1f62e"),
    EmojiNCS("hushed", "1f62f"),
    EmojiNCS("astonished", "1f632"),
    EmojiNCS("flushed", "1f633"),
    EmojiNCS("frowning", "1f626"),
    EmojiNCS("anguished", "1f627"),
    EmojiNCS("fear", "1f628"),
    EmojiNCS("cold_sweat", "1f630"),
    EmojiNCS("exhausted", "1f625"),
    EmojiNCS("cry", "1f622"),
    EmojiNCS("sob", "1f62d"),
    EmojiNCS("scream", "1f631"),
    EmojiNCS("confounded", "1f616"),
    EmojiNCS("persevere", "1f623"),
    EmojiNCS("disappointed", "1f61e"),
    EmojiNCS("sweat", "1f613"),
    EmojiNCS("weary", "1f629"),
    EmojiNCS("anguish", "1f62b"),
    EmojiNCS("triumph", "1f624"),
    EmojiNCS("rage", "1f621"),
    EmojiNCS("angry", "1f620"),
    EmojiNCS("smiling_devil", "1f608"),
    EmojiNCS("devil", "1f47f"),
    EmojiNCS("skull", "1f480"),
    EmojiNCS("skull_and_crossbones", "2620"),
    EmojiNCS("poop", "1f4a9"),
    EmojiNCS("clown", "1f921"),
    EmojiNCS("ogre", "1f479"),
    EmojiNCS("goblin", "1f47a"),
    EmojiNCS("ghost", "1f47b"),
    EmojiNCS("alien", "1f47d"),
    EmojiNCS("space_invader", "1f47e"),
    EmojiNCS("robot", "1f916"),
    EmojiNCS("smiley_cat", "1f63a"),
    EmojiNCS("smile_cat", "1f638"),
    EmojiNCS("joy_cat", "1f639"),
    EmojiNCS("heart_eyes_cat", "1f63b"),
    EmojiNCS("smirk_cat", "1f63c"),
    EmojiNCS("kissing_cat", "1f63d"),
    EmojiNCS("scream_cat", "1f640"),
    EmojiNCS("crying_cat", "1f63f"),
    EmojiNCS("angry_cat", "1f63e"),
    EmojiNCS("see_no_evil", "1f648"),
    EmojiNCS("hear_no_evil", "1f649"),
    EmojiNCS("speak_no_evil", "1f64a"),
    EmojiNCS("lipstick_kiss", "1f48b"),
    EmojiNCS("love_letter", "1f48c"),
    EmojiNCS("cupid", "1f498"),
    EmojiNCS("gift_heart", "1f49d"),
    EmojiNCS("sparkling_heart", "1f496"),
    EmojiNCS("heart_pulse", "1f497"),
    EmojiNCS("heartbeat", "1f493"),
    EmojiNCS("revolving_hearts", "1f49e"),
    EmojiNCS("two_hearts", "1f495"),
    EmojiNCS("heart_box", "1f49f"),
    EmojiNCS("heart_exclamation", "2763"),
    EmojiNCS("broken_heart", "1f494"),
    EmojiNCS("heart", "2764"),
    EmojiNCS("yellow_heart", "1f49b"),
    EmojiNCS("green_heart", "1f49a"),
    EmojiNCS("blue_heart", "1f499"),
    EmojiNCS("purple_heart", "1f49c"),
    EmojiNCS("black_heart", "1f5a4"),
    EmojiNCS("100", "1f4af"),
    EmojiNCS("anger", "1f4a2"),
    EmojiNCS("boom", "1f4a5"),
    EmojiNCS("seeing_stars", "1f4ab"),
    EmojiNCS("sweat_drops", "1f4a6"),
    EmojiNCS("dash", "1f4a8"),
    EmojiNCS("hole", "1f573"),
    EmojiNCS("bomb", "1f4a3"),
    EmojiNCS("umm", "1f4ac"),
    EmojiNCS("speech_bubble", "1f5e8"),
    EmojiNCS("anger_bubble", "1f5ef"),
    EmojiNCS("thought", "1f4ad"),
    EmojiNCS("zzz", "1f4a4"),

// People & Body
    EmojiNCS("wave", "1f44b"),
    EmojiNCS("stop", "1f91a"),
    EmojiNCS("high_five", "1f590"),
    EmojiNCS("hand", "270b"),
    EmojiNCS("spock", "1f596"),
    EmojiNCS("ok", "1f44c"),
    EmojiNCS("peace_sign", "270c"),
    EmojiNCS("fingers_crossed", "1f91e"),
    EmojiNCS("rock_on", "1f918"),
    EmojiNCS("call_me", "1f919"),
    EmojiNCS("point_left", "1f448"),
    EmojiNCS("point_right", "1f449"),
    EmojiNCS("point_up", "1f446"),
    EmojiNCS("middle_finger", "1f595"),
    EmojiNCS("point_down", "1f447"),
    EmojiNCS("wait_one_second", "261d"),
    EmojiNCS("+1", "1f44d"),
    EmojiNCS("-1", "1f44e"),
    EmojiNCS("fist", "270a"),
    EmojiNCS("fist_bump", "1f44a"),
    EmojiNCS("left_fist", "1f91b"),
    EmojiNCS("right_fist", "1f91c"),
    EmojiNCS("clap", "1f44f"),
    EmojiNCS("raised_hands", "1f64c"),
    EmojiNCS("open_hands", "1f450"),
    EmojiNCS("handshake", "1f91d"),
    EmojiNCS("pray", "1f64f"),
    EmojiNCS("writing", "270d"),
    EmojiNCS("nail_polish", "1f485"),
    EmojiNCS("selfie", "1f933"),
    EmojiNCS("muscle", "1f4aa"),
    EmojiNCS("ear", "1f442"),
    EmojiNCS("nose", "1f443"),
    EmojiNCS("eyes", "1f440"),
    EmojiNCS("eye", "1f441"),
    EmojiNCS("tongue", "1f445"),
    EmojiNCS("lips", "1f444"),
    EmojiNCS("baby", "1f476"),
    EmojiNCS("boy", "1f466"),
    EmojiNCS("girl", "1f467"),
    EmojiNCS("man", "1f468"),
    EmojiNCS("woman", "1f469"),
    EmojiNCS("older_man", "1f474"),
    EmojiNCS("older_woman", "1f475"),
    EmojiNCS("person_frowning", "1f64d"),
    EmojiNCS("person_pouting", "1f64e"),
    EmojiNCS("no_signal", "1f645"),
    EmojiNCS("ok_signal", "1f646"),
    EmojiNCS("information_desk_person", "1f481"),
    EmojiNCS("raising_hand", "1f64b"),
    EmojiNCS("bow", "1f647"),
    EmojiNCS("face_palm", "1f926"),
    EmojiNCS("shrug", "1f937"),
    EmojiNCS("police", "1f46e"),
    EmojiNCS("detective", "1f575"),
    EmojiNCS("guard", "1f482"),
    EmojiNCS("construction_worker", "1f477"),
    EmojiNCS("prince", "1f934"),
    EmojiNCS("princess", "1f478"),
    EmojiNCS("turban", "1f473"),
    EmojiNCS("gua_pi_mao", "1f472"),
    EmojiNCS("bride", "1f470"),
    EmojiNCS("pregnant", "1f930"),
    EmojiNCS("angel", "1f47c"),
    EmojiNCS("santa", "1f385"),
    EmojiNCS("mother_christmas", "1f936"),
    EmojiNCS("massage", "1f486"),
    EmojiNCS("haircut", "1f487"),
    EmojiNCS("walking", "1f6b6"),
    EmojiNCS("running", "1f3c3"),
    EmojiNCS("dancer", "1f483"),
    EmojiNCS("dancing", "1f57a"),
    EmojiNCS("levitating", "1f574"),
    EmojiNCS("dancers", "1f46f"),
    EmojiNCS("fencing", "1f93a"),
    EmojiNCS("horse_racing", "1f3c7"),
    EmojiNCS("skier", "26f7"),
    EmojiNCS("snowboarder", "1f3c2"),
    EmojiNCS("golf", "1f3cc"),
    EmojiNCS("surf", "1f3c4"),
    EmojiNCS("rowboat", "1f6a3"),
    EmojiNCS("swim", "1f3ca"),
    EmojiNCS("ball", "26f9"),
    EmojiNCS("lift", "1f3cb"),
    EmojiNCS("cyclist", "1f6b4"),
    EmojiNCS("mountain_biker", "1f6b5"),
    EmojiNCS("cartwheel", "1f938"),
    EmojiNCS("wrestling", "1f93c"),
    EmojiNCS("water_polo", "1f93d"),
    EmojiNCS("handball", "1f93e"),
    EmojiNCS("juggling", "1f939"),
    EmojiNCS("bath", "1f6c0"),
    EmojiNCS("in_bed", "1f6cc"),
    EmojiNCS("two_women_holding_hands", "1f46d"),
    EmojiNCS("man_and_woman_holding_hands", "1f46b"),
    EmojiNCS("two_men_holding_hands", "1f46c"),
    EmojiNCS("family", "1f46a"),
    EmojiNCS("speaking_head", "1f5e3"),
    EmojiNCS("silhouette", "1f464"),
    EmojiNCS("silhouettes", "1f465"),
    EmojiNCS("footprints", "1f463"),
    EmojiNCS("tuxedo", "1f935"),

// Animals & Nature
    EmojiNCS("monkey_face", "1f435"),
    EmojiNCS("monkey", "1f412"),
    EmojiNCS("gorilla", "1f98d"),
    EmojiNCS("puppy", "1f436"),
    EmojiNCS("dog", "1f415"),
    EmojiNCS("poodle", "1f429"),
    EmojiNCS("wolf", "1f43a"),
    EmojiNCS("fox", "1f98a"),
    EmojiNCS("kitten", "1f431"),
    EmojiNCS("cat", "1f408"),
    EmojiNCS("lion", "1f981"),
    EmojiNCS("tiger_cub", "1f42f"),
    EmojiNCS("tiger", "1f405"),
    EmojiNCS("leopard", "1f406"),
    EmojiNCS("pony", "1f434"),
    EmojiNCS("horse", "1f40e"),
    EmojiNCS("unicorn", "1f984"),
    EmojiNCS("deer", "1f98c"),
    EmojiNCS("calf", "1f42e"),
    EmojiNCS("ox", "1f402"),
    EmojiNCS("water_buffalo", "1f403"),
    EmojiNCS("cow", "1f404"),
    EmojiNCS("piglet", "1f437"),
    EmojiNCS("pig", "1f416"),
    EmojiNCS("boar", "1f417"),
    EmojiNCS("pig_nose", "1f43d"),
    EmojiNCS("ram", "1f40f"),
    EmojiNCS("sheep", "1f411"),
    EmojiNCS("goat", "1f410"),
    EmojiNCS("arabian_camel", "1f42a"),
    EmojiNCS("camel", "1f42b"),
    EmojiNCS("elephant", "1f418"),
    EmojiNCS("rhinoceros", "1f98f"),
    EmojiNCS("dormouse", "1f42d"),
    EmojiNCS("mouse", "1f401"),
    EmojiNCS("rat", "1f400"),
    EmojiNCS("hamster", "1f439"),
    EmojiNCS("bunny", "1f430"),
    EmojiNCS("rabbit", "1f407"),
    EmojiNCS("chipmunk", "1f43f"),
    EmojiNCS("bat", "1f987"),
    EmojiNCS("bear", "1f43b"),
    EmojiNCS("koala", "1f428"),
    EmojiNCS("panda", "1f43c"),
    EmojiNCS("paw_prints", "1f43e"),
    EmojiNCS("turkey", "1f983"),
    EmojiNCS("chicken", "1f414"),
    EmojiNCS("rooster", "1f413"),
    EmojiNCS("hatching", "1f423"),
    EmojiNCS("chick", "1f424"),
    EmojiNCS("new_baby", "1f425"),
    EmojiNCS("bird", "1f426"),
    EmojiNCS("penguin", "1f427"),
    EmojiNCS("dove", "1f54a"),
    EmojiNCS("eagle", "1f985"),
    EmojiNCS("duck", "1f986"),
    EmojiNCS("owl", "1f989"),
    EmojiNCS("frog", "1f438"),
    EmojiNCS("crocodile", "1f40a"),
    EmojiNCS("turtle", "1f422"),
    EmojiNCS("lizard", "1f98e"),
    EmojiNCS("snake", "1f40d"),
    EmojiNCS("dragon_face", "1f432"),
    EmojiNCS("dragon", "1f409"),
    EmojiNCS("whale", "1f433"),
    EmojiNCS("humpback_whale", "1f40b"),
    EmojiNCS("dolphin", "1f42c"),
    EmojiNCS("fish", "1f41f"),
    EmojiNCS("tropical_fish", "1f420"),
    EmojiNCS("blowfish", "1f421"),
    EmojiNCS("shark", "1f988"),
    EmojiNCS("octopus", "1f419"),
    EmojiNCS("shell", "1f41a"),
    EmojiNCS("snail", "1f40c"),
    EmojiNCS("butterfly", "1f98b"),
    EmojiNCS("bug", "1f41b"),
    EmojiNCS("ant", "1f41c"),
    EmojiNCS("bee", "1f41d"),
    EmojiNCS("spider", "1f577"),
    EmojiNCS("web", "1f578"),
    EmojiNCS("scorpion", "1f982"),
    EmojiNCS("bouquet", "1f490"),
    EmojiNCS("cherry_blossom", "1f338"),
    EmojiNCS("white_flower", "1f4ae"),
    EmojiNCS("rosette", "1f3f5"),
    EmojiNCS("rose", "1f339"),
    EmojiNCS("wilted_flower", "1f940"),
    EmojiNCS("hibiscus", "1f33a"),
    EmojiNCS("sunflower", "1f33b"),
    EmojiNCS("blossom", "1f33c"),
    EmojiNCS("tulip", "1f337"),
    EmojiNCS("seedling", "1f331"),
    EmojiNCS("evergreen_tree", "1f332"),
    EmojiNCS("tree", "1f333"),
    EmojiNCS("palm_tree", "1f334"),
    EmojiNCS("cactus", "1f335"),
    EmojiNCS("harvest", "1f33e"),
    EmojiNCS("herb", "1f33f"),
    EmojiNCS("shamrock", "2618"),
    EmojiNCS("lucky", "1f340"),
    EmojiNCS("maple_leaf", "1f341"),
    EmojiNCS("fallen_leaf", "1f342"),
    EmojiNCS("leaves", "1f343"),
    EmojiNCS("beetle", "1f41e"),

// Food & Drink
    EmojiNCS("grapes", "1f347"),
    EmojiNCS("melon", "1f348"),
    EmojiNCS("watermelon", "1f349"),
    EmojiNCS("orange", "1f34a"),
    EmojiNCS("lemon", "1f34b"),
    EmojiNCS("banana", "1f34c"),
    EmojiNCS("pineapple", "1f34d"),
    EmojiNCS("apple", "1f34e"),
    EmojiNCS("green_apple", "1f34f"),
    EmojiNCS("pear", "1f350"),
    EmojiNCS("peach", "1f351"),
    EmojiNCS("cherries", "1f352"),
    EmojiNCS("strawberry", "1f353"),
    EmojiNCS("kiwi", "1f95d"),
    EmojiNCS("tomato", "1f345"),
    EmojiNCS("avocado", "1f951"),
    EmojiNCS("eggplant", "1f346"),
    EmojiNCS("potato", "1f954"),
    EmojiNCS("carrot", "1f955"),
    EmojiNCS("corn", "1f33d"),
    EmojiNCS("hot_pepper", "1f336"),
    EmojiNCS("cucumber", "1f952"),
    EmojiNCS("mushroom", "1f344"),
    EmojiNCS("peanuts", "1f95c"),
    EmojiNCS("chestnut", "1f330"),
    EmojiNCS("bread", "1f35e"),
    EmojiNCS("croissant", "1f950"),
    EmojiNCS("baguette", "1f956"),
    EmojiNCS("pancakes", "1f95e"),
    EmojiNCS("cheese", "1f9c0"),
    EmojiNCS("meat", "1f356"),
    EmojiNCS("drumstick", "1f357"),
    EmojiNCS("bacon", "1f953"),
    EmojiNCS("hamburger", "1f354"),
    EmojiNCS("fries", "1f35f"),
    EmojiNCS("pizza", "1f355"),
    EmojiNCS("hotdog", "1f32d"),
    EmojiNCS("taco", "1f32e"),
    EmojiNCS("burrito", "1f32f"),
    EmojiNCS("doner_kebab", "1f959"),
    EmojiNCS("egg", "1f95a"),
    EmojiNCS("cooking", "1f373"),
    EmojiNCS("paella", "1f958"),
    EmojiNCS("food", "1f372"),
    EmojiNCS("salad", "1f957"),
    EmojiNCS("popcorn", "1f37f"),
    EmojiNCS("bento", "1f371"),
    EmojiNCS("senbei", "1f358"),
    EmojiNCS("onigiri", "1f359"),
    EmojiNCS("rice", "1f35a"),
    EmojiNCS("curry", "1f35b"),
    EmojiNCS("ramen", "1f35c"),
    EmojiNCS("spaghetti", "1f35d"),
    EmojiNCS("yam", "1f360"),
    EmojiNCS("oden", "1f362"),
    EmojiNCS("sushi", "1f363"),
    EmojiNCS("tempura", "1f364"),
    EmojiNCS("naruto", "1f365"),
    EmojiNCS("dango", "1f361"),
    EmojiNCS("crab", "1f980"),
    EmojiNCS("shrimp", "1f990"),
    EmojiNCS("squid", "1f991"),
    EmojiNCS("soft_serve", "1f366"),
    EmojiNCS("shaved_ice", "1f367"),
    EmojiNCS("ice_cream", "1f368"),
    EmojiNCS("donut", "1f369"),
    EmojiNCS("cookie", "1f36a"),
    EmojiNCS("birthday", "1f382"),
    EmojiNCS("cake", "1f370"),
    EmojiNCS("chocolate", "1f36b"),
    EmojiNCS("candy", "1f36c"),
    EmojiNCS("lollipop", "1f36d"),
    EmojiNCS("custard", "1f36e"),
    EmojiNCS("honey", "1f36f"),
    EmojiNCS("baby_bottle", "1f37c"),
    EmojiNCS("milk", "1f95b"),
    EmojiNCS("coffee", "2615"),
    EmojiNCS("tea", "1f375"),
    EmojiNCS("sake", "1f376"),
    EmojiNCS("champagne", "1f37e"),
    EmojiNCS("wine", "1f377"),
    EmojiNCS("cocktail", "1f378"),
    EmojiNCS("tropical_drink", "1f379"),
    EmojiNCS("beer", "1f37a"),
    EmojiNCS("beers", "1f37b"),
    EmojiNCS("clink", "1f942"),
    EmojiNCS("small_glass", "1f943"),
    EmojiNCS("hungry", "1f37d"),
    EmojiNCS("fork_and_knife", "1f374"),
    EmojiNCS("spoon", "1f944"),
    EmojiNCS("knife", "1f52a"),
    EmojiNCS("vase", "1f3fa"),

// Activities
    EmojiNCS("jack-o-lantern", "1f383"),
    EmojiNCS("holiday_tree", "1f384"),
    EmojiNCS("fireworks", "1f386"),
    EmojiNCS("sparkler", "1f387"),
    EmojiNCS("sparkles", "2728"),
    EmojiNCS("balloon", "1f388"),
    EmojiNCS("tada", "1f389"),
    EmojiNCS("confetti", "1f38a"),
    EmojiNCS("wish_tree", "1f38b"),
    EmojiNCS("bamboo", "1f38d"),
    EmojiNCS("dolls", "1f38e"),
    EmojiNCS("carp_streamer", "1f38f"),
    EmojiNCS("wind_chime", "1f390"),
    EmojiNCS("moon_ceremony", "1f391"),
    EmojiNCS("ribbon", "1f380"),
    EmojiNCS("gift", "1f381"),
    EmojiNCS("reminder_ribbon", "1f397"),
    EmojiNCS("ticket", "1f39f"),
    EmojiNCS("pass", "1f3ab"),
    EmojiNCS("military_medal", "1f396"),
    EmojiNCS("trophy", "1f3c6"),
    EmojiNCS("medal", "1f3c5"),
    EmojiNCS("first_place", "1f947"),
    EmojiNCS("second_place", "1f948"),
    EmojiNCS("third_place", "1f949"),
    EmojiNCS("football", "26bd"),
    EmojiNCS("baseball", "26be"),
    EmojiNCS("basketball", "1f3c0"),
    EmojiNCS("volleyball", "1f3d0"),
    EmojiNCS("american_football", "1f3c8"),
    EmojiNCS("rugby", "1f3c9"),
    EmojiNCS("tennis", "1f3be"),
    EmojiNCS("strike", "1f3b3"),
    EmojiNCS("cricket", "1f3cf"),
    EmojiNCS("field_hockey", "1f3d1"),
    EmojiNCS("ice_hockey", "1f3d2"),
    EmojiNCS("ping_pong", "1f3d3"),
    EmojiNCS("badminton", "1f3f8"),
    EmojiNCS("boxing_glove", "1f94a"),
    EmojiNCS("black_belt", "1f94b"),
    EmojiNCS("gooooooooal", "1f945"),
    EmojiNCS("hole_in_one", "26f3"),
    EmojiNCS("ice_skate", "26f8"),
    EmojiNCS("fishing", "1f3a3"),
    EmojiNCS("running_shirt", "1f3bd"),
    EmojiNCS("ski", "1f3bf"),
    EmojiNCS("direct_hit", "1f3af"),
    EmojiNCS("billiards", "1f3b1"),
    EmojiNCS("crystal_ball", "1f52e"),
    EmojiNCS("video_game", "1f3ae"),
    EmojiNCS("joystick", "1f579"),
    EmojiNCS("slot_machine", "1f3b0"),
    EmojiNCS("dice", "1f3b2"),
    EmojiNCS("spades", "2660"),
    EmojiNCS("hearts", "2665"),
    EmojiNCS("diamonds", "2666"),
    EmojiNCS("clubs", "2663"),
    EmojiNCS("joker", "1f0cf"),
    EmojiNCS("mahjong", "1f004"),
    EmojiNCS("playing_cards", "1f3b4"),
    EmojiNCS("performing_arts", "1f3ad"),
    EmojiNCS("picture", "1f5bc"),
    EmojiNCS("art", "1f3a8"),

// Travel & Places
    EmojiNCS("earth_africa", "1f30d"),
    EmojiNCS("earth_americas", "1f30e"),
    EmojiNCS("earth_asia", "1f30f"),
    EmojiNCS("www", "1f310"),
    EmojiNCS("map", "1f5fa"),
    EmojiNCS("japan", "1f5fe"),
    EmojiNCS("snowy_mountain", "1f3d4"),
    EmojiNCS("mountain", "26f0"),
    EmojiNCS("volcano", "1f30b"),
    EmojiNCS("mount_fuji", "1f5fb"),
    EmojiNCS("campsite", "1f3d5"),
    EmojiNCS("beach", "1f3d6"),
    EmojiNCS("desert", "1f3dc"),
    EmojiNCS("island", "1f3dd"),
    EmojiNCS("national_park", "1f3de"),
    EmojiNCS("stadium", "1f3df"),
    EmojiNCS("classical_building", "1f3db"),
    EmojiNCS("construction", "1f3d7"),
    EmojiNCS("houses", "1f3d8"),
    EmojiNCS("derelict_house", "1f3da"),
    EmojiNCS("house", "1f3e0"),
    EmojiNCS("suburb", "1f3e1"),
    EmojiNCS("office", "1f3e2"),
    EmojiNCS("japan_post", "1f3e3"),
    EmojiNCS("post_office", "1f3e4"),
    EmojiNCS("hospital", "1f3e5"),
    EmojiNCS("bank", "1f3e6"),
    EmojiNCS("hotel", "1f3e8"),
    EmojiNCS("love_hotel", "1f3e9"),
    EmojiNCS("convenience_store", "1f3ea"),
    EmojiNCS("school", "1f3eb"),
    EmojiNCS("department_store", "1f3ec"),
    EmojiNCS("factory", "1f3ed"),
    EmojiNCS("shiro", "1f3ef"),
    EmojiNCS("castle", "1f3f0"),
    EmojiNCS("wedding", "1f492"),
    EmojiNCS("tower", "1f5fc"),
    EmojiNCS("statue", "1f5fd"),
    EmojiNCS("church", "26ea"),
    EmojiNCS("mosque", "1f54c"),
    EmojiNCS("synagogue", "1f54d"),
    EmojiNCS("shinto_shrine", "26e9"),
    EmojiNCS("kaaba", "1f54b"),
    EmojiNCS("fountain", "26f2"),
    EmojiNCS("tent", "26fa"),
    EmojiNCS("foggy", "1f301"),
    EmojiNCS("night", "1f303"),
    EmojiNCS("city", "1f3d9"),
    EmojiNCS("mountain_sunrise", "1f304"),
    EmojiNCS("sunrise", "1f305"),
    EmojiNCS("sunset", "1f306"),
    EmojiNCS("city_sunrise", "1f307"),
    EmojiNCS("bridge", "1f309"),
    EmojiNCS("hot_springs", "2668"),
    EmojiNCS("carousel", "1f3a0"),
    EmojiNCS("ferris_wheel", "1f3a1"),
    EmojiNCS("roller_coaster", "1f3a2"),
    EmojiNCS("barber", "1f488"),
    EmojiNCS("circus", "1f3aa"),
    EmojiNCS("train", "1f682"),
    EmojiNCS("railway_car", "1f683"),
    EmojiNCS("high_speed_train", "1f684"),
    EmojiNCS("bullet_train", "1f685"),
    EmojiNCS("oncoming_train", "1f686"),
    EmojiNCS("subway", "1f687"),
    EmojiNCS("light_rail", "1f688"),
    EmojiNCS("station", "1f689"),
    EmojiNCS("oncoming_tram", "1f68a"),
    EmojiNCS("monorail", "1f69d"),
    EmojiNCS("mountain_railway", "1f69e"),
    EmojiNCS("tram", "1f68b"),
    EmojiNCS("bus", "1f68c"),
    EmojiNCS("oncoming_bus", "1f68d"),
    EmojiNCS("trolley", "1f68e"),
    EmojiNCS("minibus", "1f690"),
    EmojiNCS("ambulance", "1f691"),
    EmojiNCS("fire_truck", "1f692"),
    EmojiNCS("police_car", "1f693"),
    EmojiNCS("oncoming_police_car", "1f694"),
    EmojiNCS("taxi", "1f695"),
    EmojiNCS("oncoming_taxi", "1f696"),
    EmojiNCS("car", "1f697"),
    EmojiNCS("oncoming_car", "1f698"),
    EmojiNCS("recreational_vehicle", "1f699"),
    EmojiNCS("moving_truck", "1f69a"),
    EmojiNCS("truck", "1f69b"),
    EmojiNCS("tractor", "1f69c"),
    EmojiNCS("racecar", "1f3ce"),
    EmojiNCS("motorcycle", "1f3cd"),
    EmojiNCS("scooter", "1f6f5"),
    EmojiNCS("bike", "1f6b2"),
    EmojiNCS("kick_scooter", "1f6f4"),
    EmojiNCS("bus_stop", "1f68f"),
    EmojiNCS("road", "1f6e3"),
    EmojiNCS("railway_track", "1f6e4"),
    EmojiNCS("oil_drum", "1f6e2"),
    EmojiNCS("fuel_pump", "26fd"),
    EmojiNCS("siren", "1f6a8"),
    EmojiNCS("horizontal_traffic_light", "1f6a5"),
    EmojiNCS("traffic_light", "1f6a6"),
    EmojiNCS("stop_sign", "1f6d1"),
    EmojiNCS("work_in_progress", "1f6a7"),
    EmojiNCS("anchor", "2693"),
    EmojiNCS("boat", "26f5"),
    EmojiNCS("canoe", "1f6f6"),
    EmojiNCS("speedboat", "1f6a4"),
    EmojiNCS("passenger_ship", "1f6f3"),
    EmojiNCS("ferry", "26f4"),
    EmojiNCS("motor_boat", "1f6e5"),
    EmojiNCS("ship", "1f6a2"),
    EmojiNCS("airplane", "2708"),
    EmojiNCS("small_airplane", "1f6e9"),
    EmojiNCS("take_off", "1f6eb"),
    EmojiNCS("landing", "1f6ec"),
    EmojiNCS("seat", "1f4ba"),
    EmojiNCS("helicopter", "1f681"),
    EmojiNCS("suspension_railway", "1f69f"),
    EmojiNCS("gondola", "1f6a0"),
    EmojiNCS("aerial_tramway", "1f6a1"),
    EmojiNCS("satellite", "1f6f0"),
    EmojiNCS("rocket", "1f680"),
    EmojiNCS("bellhop_bell", "1f6ce"),
    EmojiNCS("times_up", "231b"),
    EmojiNCS("time_ticking", "23f3"),
    EmojiNCS("watch", "231a"),
    EmojiNCS("alarm_clock", "23f0"),
    EmojiNCS("stopwatch", "23f1"),
    EmojiNCS("timer", "23f2"),
    EmojiNCS("mantelpiece_clock", "1f570"),
    EmojiNCS("time", "1f557"),
    EmojiNCS("new_moon", "1f311"),
    EmojiNCS("waxing_moon", "1f314"),
    EmojiNCS("full_moon", "1f315"),
    EmojiNCS("moon", "1f319"),
    EmojiNCS("new_moon_face", "1f31a"),
    EmojiNCS("goodnight", "1f31b"),
    EmojiNCS("temperature", "1f321"),
    EmojiNCS("sunny", "2600"),
    EmojiNCS("moon_face", "1f31d"),
    EmojiNCS("sun_face", "1f31e"),
    EmojiNCS("star", "2b50"),
    EmojiNCS("glowing_star", "1f31f"),
    EmojiNCS("shooting_star", "1f320"),
    EmojiNCS("milky_way", "1f30c"),
    EmojiNCS("cloud", "2601"),
    EmojiNCS("partly_sunny", "26c5"),
    EmojiNCS("thunderstorm", "26c8"),
    EmojiNCS("mostly_sunny", "1f324"),
    EmojiNCS("cloudy", "1f325"),
    EmojiNCS("sunshowers", "1f326"),
    EmojiNCS("rainy", "1f327"),
    EmojiNCS("snowy", "1f328"),
    EmojiNCS("lightning", "1f329"),
    EmojiNCS("tornado", "1f32a"),
    EmojiNCS("fog", "1f32b"),
    EmojiNCS("windy", "1f32c"),
    EmojiNCS("cyclone", "1f300"),
    EmojiNCS("rainbow", "1f308"),
    EmojiNCS("closed_umbrella", "1f302"),
    EmojiNCS("umbrella", "2602"),
    EmojiNCS("umbrella_with_rain", "2614"),
    EmojiNCS("beach_umbrella", "26f1"),
    EmojiNCS("high_voltage", "26a1"),
    EmojiNCS("snowflake", "2744"),
    EmojiNCS("snowman", "2603"),
    EmojiNCS("frosty", "26c4"),
    EmojiNCS("comet", "2604"),
    EmojiNCS("fire", "1f525"),
    EmojiNCS("drop", "1f4a7"),
    EmojiNCS("ocean", "1f30a"),

// Objects
    EmojiNCS("glasses", "1f453"),
    EmojiNCS("dark_sunglasses", "1f576"),
    EmojiNCS("tie", "1f454"),
    EmojiNCS("shirt", "1f455"),
    EmojiNCS("jeans", "1f456"),
    EmojiNCS("dress", "1f457"),
    EmojiNCS("kimono", "1f458"),
    EmojiNCS("bikini", "1f459"),
    EmojiNCS("clothing", "1f45a"),
    EmojiNCS("purse", "1f45b"),
    EmojiNCS("handbag", "1f45c"),
    EmojiNCS("pouch", "1f45d"),
    EmojiNCS("shopping_bags", "1f6cd"),
    EmojiNCS("backpack", "1f392"),
    EmojiNCS("shoe", "1f45e"),
    EmojiNCS("athletic_shoe", "1f45f"),
    EmojiNCS("high_heels", "1f460"),
    EmojiNCS("sandal", "1f461"),
    EmojiNCS("boot", "1f462"),
    EmojiNCS("crown", "1f451"),
    EmojiNCS("hat", "1f452"),
    EmojiNCS("top_hat", "1f3a9"),
    EmojiNCS("graduate", "1f393"),
    EmojiNCS("helmet", "26d1"),
    EmojiNCS("prayer_beads", "1f4ff"),
    EmojiNCS("lipstick", "1f484"),
    EmojiNCS("ring", "1f48d"),
    EmojiNCS("gem", "1f48e"),
    EmojiNCS("mute", "1f507"),
    EmojiNCS("speaker", "1f508"),
    EmojiNCS("softer", "1f509"),
    EmojiNCS("louder", "1f50a"),
    EmojiNCS("loudspeaker", "1f4e2"),
    EmojiNCS("megaphone", "1f4e3"),
    EmojiNCS("horn", "1f4ef"),
    EmojiNCS("notifications", "1f514"),
    EmojiNCS("mute_notifications", "1f515"),
    EmojiNCS("musical_score", "1f3bc"),
    EmojiNCS("music", "1f3b5"),
    EmojiNCS("musical_notes", "1f3b6"),
    EmojiNCS("studio_microphone", "1f399"),
    EmojiNCS("volume", "1f39a"),
    EmojiNCS("control_knobs", "1f39b"),
    EmojiNCS("microphone", "1f3a4"),
    EmojiNCS("headphones", "1f3a7"),
    EmojiNCS("radio", "1f4fb"),
    EmojiNCS("saxophone", "1f3b7"),
    EmojiNCS("guitar", "1f3b8"),
    EmojiNCS("piano", "1f3b9"),
    EmojiNCS("trumpet", "1f3ba"),
    EmojiNCS("violin", "1f3bb"),
    EmojiNCS("drum", "1f941"),
    EmojiNCS("mobile_phone", "1f4f1"),
    EmojiNCS("calling", "1f4f2"),
    EmojiNCS("phone", "260e"),
    EmojiNCS("landline", "1f4de"),
    EmojiNCS("pager", "1f4df"),
    EmojiNCS("fax", "1f4e0"),
    EmojiNCS("battery", "1f50b"),
    EmojiNCS("electric_plug", "1f50c"),
    EmojiNCS("computer", "1f4bb"),
    EmojiNCS("desktop_computer", "1f5a5"),
    EmojiNCS("printer", "1f5a8"),
    EmojiNCS("keyboard", "2328"),
    EmojiNCS("computer_mouse", "1f5b1"),
    EmojiNCS("trackball", "1f5b2"),
    EmojiNCS("gold_record", "1f4bd"),
    EmojiNCS("floppy_disk", "1f4be"),
    EmojiNCS("cd", "1f4bf"),
    EmojiNCS("dvd", "1f4c0"),
    EmojiNCS("movie_camera", "1f3a5"),
    EmojiNCS("film", "1f39e"),
    EmojiNCS("projector", "1f4fd"),
    EmojiNCS("action", "1f3ac"),
    EmojiNCS("tv", "1f4fa"),
    EmojiNCS("camera", "1f4f7"),
    EmojiNCS("taking_a_picture", "1f4f8"),
    EmojiNCS("video_camera", "1f4f9"),
    EmojiNCS("vhs", "1f4fc"),
    EmojiNCS("search", "1f50d"),
    EmojiNCS("candle", "1f56f"),
    EmojiNCS("light_bulb", "1f4a1"),
    EmojiNCS("flashlight", "1f526"),
    EmojiNCS("lantern", "1f3ee"),
    EmojiNCS("decorative_notebook", "1f4d4"),
    EmojiNCS("red_book", "1f4d5"),
    EmojiNCS("book", "1f4d6"),
    EmojiNCS("green_book", "1f4d7"),
    EmojiNCS("blue_book", "1f4d8"),
    EmojiNCS("orange_book", "1f4d9"),
    EmojiNCS("books", "1f4da"),
    EmojiNCS("notebook", "1f4d3"),
    EmojiNCS("ledger", "1f4d2"),
    EmojiNCS("receipt", "1f4c3"),
    EmojiNCS("scroll", "1f4dc"),
    EmojiNCS("document", "1f4c4"),
    EmojiNCS("headlines", "1f4f0"),
    EmojiNCS("newspaper", "1f5de"),
    EmojiNCS("place_holder", "1f4d1"),
    EmojiNCS("bookmark", "1f516"),
    EmojiNCS("label", "1f3f7"),
    EmojiNCS("money", "1f4b0"),
    EmojiNCS("yen_banknotes", "1f4b4"),
    EmojiNCS("dollar_bills", "1f4b5"),
    EmojiNCS("euro_banknotes", "1f4b6"),
    EmojiNCS("pound_notes", "1f4b7"),
    EmojiNCS("losing_money", "1f4b8"),
    EmojiNCS("credit_card", "1f4b3"),
    EmojiNCS("stock_market", "1f4b9"),
    EmojiNCS("email", "2709"),
    EmojiNCS("e-mail", "1f4e7"),
    EmojiNCS("mail_received", "1f4e8"),
    EmojiNCS("mail_sent", "1f4e9"),
    EmojiNCS("outbox", "1f4e4"),
    EmojiNCS("inbox", "1f4e5"),
    EmojiNCS("package", "1f4e6"),
    EmojiNCS("mailbox", "1f4eb"),
    EmojiNCS("closed_mailbox", "1f4ea"),
    EmojiNCS("unread_mail", "1f4ec"),
    EmojiNCS("inbox_zero", "1f4ed"),
    EmojiNCS("mail_dropoff", "1f4ee"),
    EmojiNCS("ballot_box", "1f5f3"),
    EmojiNCS("pencil", "270f"),
    EmojiNCS("fountain_pen", "1f58b"),
    EmojiNCS("pen", "1f58a"),
    EmojiNCS("paintbrush", "1f58c"),
    EmojiNCS("crayon", "1f58d"),
    EmojiNCS("memo", "1f4dd"),
    EmojiNCS("briefcase", "1f4bc"),
    EmojiNCS("organize", "1f4c1"),
    EmojiNCS("folder", "1f4c2"),
    EmojiNCS("sort", "1f5c2"),
    EmojiNCS("calendar", "1f4c5"),
    EmojiNCS("date", "1f4c6"),
    EmojiNCS("spiral_notepad", "1f5d2"),
    EmojiNCS("rolodex", "1f4c7"),
    EmojiNCS("chart", "1f4c8"),
    EmojiNCS("downwards_trend", "1f4c9"),
    EmojiNCS("bar_chart", "1f4ca"),
    EmojiNCS("clipboard", "1f4cb"),
    EmojiNCS("push_pin", "1f4cc"),
    EmojiNCS("pin", "1f4cd"),
    EmojiNCS("paperclip", "1f4ce"),
    EmojiNCS("office_supplies", "1f587"),
    EmojiNCS("ruler", "1f4cf"),
    EmojiNCS("carpenter_square", "1f4d0"),
    EmojiNCS("scissors", "2702"),
    EmojiNCS("archive", "1f5c3"),
    EmojiNCS("file_cabinet", "1f5c4"),
    EmojiNCS("wastebasket", "1f5d1"),
    EmojiNCS("locked", "1f512"),
    EmojiNCS("unlocked", "1f513"),
    EmojiNCS("privacy", "1f50f"),
    EmojiNCS("secure", "1f510"),
    EmojiNCS("key", "1f511"),
    EmojiNCS("secret", "1f5dd"),
    EmojiNCS("hammer", "1f528"),
    EmojiNCS("mine", "26cf"),
    EmojiNCS("at_work", "2692"),
    EmojiNCS("working_on_it", "1f6e0"),
    EmojiNCS("dagger", "1f5e1"),
    EmojiNCS("duel", "2694"),
    EmojiNCS("gun", "1f52b"),
    EmojiNCS("bow_and_arrow", "1f3f9"),
    EmojiNCS("shield", "1f6e1"),
    EmojiNCS("fixing", "1f527"),
    EmojiNCS("nut_and_bolt", "1f529"),
    EmojiNCS("gear", "2699"),
    EmojiNCS("compression", "1f5dc"),
    EmojiNCS("justice", "2696"),
    EmojiNCS("link", "1f517"),
    EmojiNCS("chains", "26d3"),
    EmojiNCS("alchemy", "2697"),
    EmojiNCS("science", "1f52c"),
    EmojiNCS("telescope", "1f52d"),
    EmojiNCS("satellite_antenna", "1f4e1"),
    EmojiNCS("injection", "1f489"),
    EmojiNCS("medicine", "1f48a"),
    EmojiNCS("door", "1f6aa"),
    EmojiNCS("bed", "1f6cf"),
    EmojiNCS("living_room", "1f6cb"),
    EmojiNCS("toilet", "1f6bd"),
    EmojiNCS("shower", "1f6bf"),
    EmojiNCS("bathtub", "1f6c1"),
    EmojiNCS("shopping_cart", "1f6d2"),
    EmojiNCS("smoking", "1f6ac"),
    EmojiNCS("coffin", "26b0"),
    EmojiNCS("funeral_urn", "26b1"),
    EmojiNCS("rock_carving", "1f5ff"),

// Symbols
    EmojiNCS("atm", "1f3e7"),
    EmojiNCS("put_litter_in_its_place", "1f6ae"),
    EmojiNCS("potable_water", "1f6b0"),
    EmojiNCS("accessible", "267f"),
    EmojiNCS("mens", "1f6b9"),
    EmojiNCS("womens", "1f6ba"),
    EmojiNCS("restroom", "1f6bb"),
    EmojiNCS("baby_change_station", "1f6bc"),
    EmojiNCS("wc", "1f6be"),
    EmojiNCS("passport_control", "1f6c2"),
    EmojiNCS("customs", "1f6c3"),
    EmojiNCS("baggage_claim", "1f6c4"),
    EmojiNCS("locker", "1f6c5"),
    EmojiNCS("warning", "26a0"),
    EmojiNCS("children_crossing", "1f6b8"),
    EmojiNCS("no_entry", "26d4"),
    EmojiNCS("prohibited", "1f6ab"),
    EmojiNCS("no_bicycles", "1f6b3"),
    EmojiNCS("no_smoking", "1f6ad"),
    EmojiNCS("do_not_litter", "1f6af"),
    EmojiNCS("non-potable_water", "1f6b1"),
    EmojiNCS("no_pedestrians", "1f6b7"),
    EmojiNCS("no_phones", "1f4f5"),
    EmojiNCS("underage", "1f51e"),
    EmojiNCS("radioactive", "2622"),
    EmojiNCS("biohazard", "2623"),
    EmojiNCS("up", "2b06"),
    EmojiNCS("upper_right", "2197"),
    EmojiNCS("right", "27a1"),
    EmojiNCS("lower_right", "2198"),
    EmojiNCS("down", "2b07"),
    EmojiNCS("lower_left", "2199"),
    EmojiNCS("left", "2b05"),
    EmojiNCS("upper_left", "2196"),
    EmojiNCS("up_down", "2195"),
    EmojiNCS("left_right", "2194"),
    EmojiNCS("reply", "21a9"),
    EmojiNCS("forward", "21aa"),
    EmojiNCS("heading_up", "2934"),
    EmojiNCS("heading_down", "2935"),
    EmojiNCS("clockwise", "1f503"),
    EmojiNCS("counterclockwise", "1f504"),
    EmojiNCS("back", "1f519"),
    EmojiNCS("end", "1f51a"),
    EmojiNCS("on", "1f51b"),
    EmojiNCS("soon", "1f51c"),
    EmojiNCS("top", "1f51d"),
    EmojiNCS("place_of_worship", "1f6d0"),
    EmojiNCS("atom", "269b"),
    EmojiNCS("om", "1f549"),
    EmojiNCS("star_of_david", "2721"),
    EmojiNCS("wheel_of_dharma", "2638"),
    EmojiNCS("yin_yang", "262f"),
    EmojiNCS("cross", "271d"),
    EmojiNCS("orthodox_cross", "2626"),
    EmojiNCS("star_and_crescent", "262a"),
    EmojiNCS("peace", "262e"),
    EmojiNCS("menorah", "1f54e"),
    EmojiNCS("aries", "2648"),
    EmojiNCS("taurus", "2649"),
    EmojiNCS("gemini", "264a"),
    EmojiNCS("cancer", "264b"),
    EmojiNCS("leo", "264c"),
    EmojiNCS("virgo", "264d"),
    EmojiNCS("libra", "264e"),
    EmojiNCS("scorpius", "264f"),
    EmojiNCS("sagittarius", "2650"),
    EmojiNCS("capricorn", "2651"),
    EmojiNCS("aquarius", "2652"),
    EmojiNCS("pisces", "2653"),
    EmojiNCS("ophiuchus", "26ce"),
    EmojiNCS("shuffle", "1f500"),
    EmojiNCS("repeat", "1f501"),
    EmojiNCS("repeat_one", "1f502"),
    EmojiNCS("play", "25b6"),
    EmojiNCS("fast_forward", "23e9"),
    EmojiNCS("next_track", "23ed"),
    EmojiNCS("play_pause", "23ef"),
    EmojiNCS("play_reverse", "25c0"),
    EmojiNCS("rewind", "23ea"),
    EmojiNCS("previous_track", "23ee"),
    EmojiNCS("upvote", "1f53c"),
    EmojiNCS("double_up", "23eb"),
    EmojiNCS("downvote", "1f53d"),
    EmojiNCS("double_down", "23ec"),
    EmojiNCS("pause", "23f8"),
    EmojiNCS("stop_button", "23f9"),
    EmojiNCS("record", "23fa"),
    EmojiNCS("cinema", "1f3a6"),
    EmojiNCS("low_brightness", "1f505"),
    EmojiNCS("brightness", "1f506"),
    EmojiNCS("cell_reception", "1f4f6"),
    EmojiNCS("vibration_mode", "1f4f3"),
    EmojiNCS("phone_off", "1f4f4"),
    EmojiNCS("multiplication", "2716"),
    EmojiNCS("plus", "2795"),
    EmojiNCS("minus", "2796"),
    EmojiNCS("division", "2797"),
    EmojiNCS("bangbang", "203c"),
    EmojiNCS("interrobang", "2049"),
    EmojiNCS("question", "2753"),
    EmojiNCS("grey_question", "2754"),
    EmojiNCS("grey_exclamation", "2755"),
    EmojiNCS("exclamation", "2757"),
    EmojiNCS("wavy_dash", "3030"),
    EmojiNCS("exchange", "1f4b1"),
    EmojiNCS("dollars", "1f4b2"),
    EmojiNCS("recycle", "267b"),
    EmojiNCS("fleur_de_lis", "269c"),
    EmojiNCS("trident", "1f531"),
    EmojiNCS("name_badge", "1f4db"),
    EmojiNCS("beginner", "1f530"),
    EmojiNCS("circle", "2b55"),
    EmojiNCS("check", "2705"),
    EmojiNCS("checkbox", "2611"),
    EmojiNCS("check_mark", "2714"),
    EmojiNCS("cross_mark", "274c"),
    EmojiNCS("x", "274e"),
    EmojiNCS("loop", "27b0"),
    EmojiNCS("double_loop", "27bf"),
    EmojiNCS("part_alternation", "303d"),
    EmojiNCS("eight_spoked_asterisk", "2733"),
    EmojiNCS("eight_pointed_star", "2734"),
    EmojiNCS("sparkle", "2747"),
    EmojiNCS("tm", "2122"),
    EmojiNCS("hash", "0023"),
    EmojiNCS("asterisk", "002a"),
    EmojiNCS("zero", "0030"),
    EmojiNCS("one", "0031"),
    EmojiNCS("two", "0032"),
    EmojiNCS("three", "0033"),
    EmojiNCS("four", "0034"),
    EmojiNCS("five", "0035"),
    EmojiNCS("six", "0036"),
    EmojiNCS("seven", "0037"),
    EmojiNCS("eight", "0038"),
    EmojiNCS("nine", "0039"),
    EmojiNCS("ten", "1f51f"),
    EmojiNCS("capital_abcd", "1f520"),
    EmojiNCS("abcd", "1f521"),
    EmojiNCS("1234", "1f522"),
    EmojiNCS("symbols", "1f523"),
    EmojiNCS("abc", "1f524"),
    EmojiNCS("a", "1f170"),
    EmojiNCS("ab", "1f18e"),
    EmojiNCS("b", "1f171"),
    EmojiNCS("cl", "1f191"),
    EmojiNCS("cool", "1f192"),
    EmojiNCS("free", "1f193"),
    EmojiNCS("info", "2139"),
    EmojiNCS("id", "1f194"),
    EmojiNCS("metro", "24c2"),
    EmojiNCS("new", "1f195"),
    EmojiNCS("ng", "1f196"),
    EmojiNCS("o", "1f17e"),
    EmojiNCS("squared_ok", "1f197"),
    EmojiNCS("parking", "1f17f"),
    EmojiNCS("sos", "1f198"),
    EmojiNCS("squared_up", "1f199"),
    EmojiNCS("vs", "1f19a"),
    EmojiNCS("red_circle", "1f534"),
    EmojiNCS("blue_circle", "1f535"),
    EmojiNCS("black_circle", "26ab"),
    EmojiNCS("white_circle", "26aa"),
    EmojiNCS("black_large_square", "2b1b"),
    EmojiNCS("white_large_square", "2b1c"),
    EmojiNCS("black_medium_square", "25fc"),
    EmojiNCS("white_medium_square", "25fb"),
    EmojiNCS("black_medium_small_square", "25fe"),
    EmojiNCS("white_medium_small_square", "25fd"),
    EmojiNCS("black_small_square", "25aa"),
    EmojiNCS("white_small_square", "25ab"),
    EmojiNCS("large_orange_diamond", "1f536"),
    EmojiNCS("large_blue_diamond", "1f537"),
    EmojiNCS("small_orange_diamond", "1f538"),
    EmojiNCS("small_blue_diamond", "1f539"),
    EmojiNCS("red_triangle_up", "1f53a"),
    EmojiNCS("red_triangle_down", "1f53b"),
    EmojiNCS("cute", "1f4a0"),
    EmojiNCS("radio_button", "1f518"),
    EmojiNCS("black_and_white_square", "1f533"),
    EmojiNCS("white_and_black_square", "1f532"),

// Flags
    EmojiNCS("checkered_flag", "1f3c1"),
    EmojiNCS("triangular_flag", "1f6a9"),
    EmojiNCS("crossed_flags", "1f38c"),
    EmojiNCS("black_flag", "1f3f4"),
    EmojiNCS("white_flag", "1f3f3")
)



