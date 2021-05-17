import axios from "axios";

export function journalSave(valueDict, handleSuccess, handleError) {
    return axios.post('/journal_save/', valueDict,
    )
        .then(function () {
            handleSuccess()
        })
        .catch(function (error) {
            console.log(error)
            handleError()
        })
}

export function journalListGet(query, option, resultFunc) {
    resultFunc(
        [
            {pk: 'j123', name: '期刊J', ename: null, short_name: 'wer', deadline: 1618389187000, publish: 1625241600000},
            {pk: 'j234', name: null, ename: 'JournalM', type: 'j', deadline: 1625241600000, publish: 1725241600000,
                rate: [
                    {pk: 'rgaofijaeoijf', name: '评级', rate: 'S+'},
                    {pk: 'rgaofijasfdeoijf', name: 'wode评级', rate: '#+'},
                    {pk: 'rgaogsfijaeoijf', name: 'qitade评级', rate: 'S+'},
                ]},
            {pk: 'jwer23', name: 'gdfsgs', ename: null, short_name: 'wer', type: 'c', deadline: 1118389187000, publish: 1218389187000},
            {pk: 'j2erw434', name: null, ename: 'Jourwer34wernalM', type: 'j'},
            {pk: 'j134523', name: '期fsadfaer刊J', ename: null, short_name: 'wer', type: 'c'},
            {pk: 'j2terh34', name: 'faserew', ename: 'JouerwerrnalM', type: 'j'},
        ]
    )
}

export function journalRender(item) {
    return `${!item.name ? item.ename : item.name}`
}

export function journalDelete(pk){
    console.info('delete', pk)
}

export function getJournalByUid(input){
    if(input === 'j234'){
        return {
            pk: 'j234',
            name: null,
            ename: 'JournalM',
            type: 'j',
            short_name: 'wer',
            rate: [
                {pk: 'rgaofijasfdeoijf', name: 'wode评级', rate: '#+'},
                {pk: 'rgaogsfijaeoijf', name: 'qitade评级', rate: 'S+'},
            ],
            history: [
                {y: 1990, v: 23, n:1, cover: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'},
                {y: 1990, v: 24, n:1, cover: ''},
                {y: 1990, v: 25, n:1, cover: ''},
                {y: 1990, v: 23, n:1, cover: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'},
                {y: 1990, v: 24, n:1, cover: ''},
                {y: 1990, v: 25, n:1, cover: ''},
            ]
        }
    }else{
        return {
            pk: 'j14',
            name: '我的期刊',
            ename: null,
            type: 'c',
            rate: [
                {pk: 'rgaofijaeoijf', name: '评级', rate: 'S+'},
                {pk: 'rgaofijasfdeoijf', name: 'wode评级', rate: '#+'},
            ],
            history: [
                {year: 1990, date: 1618389187000, place: ''},
                {year: 1941, date: 1618389187000, place: ''},
                {year: 1992, date: 1618389187000, place: ''},
            ]
        }
    }
}

export function rateListGet(query, resultFunc) {
    return resultFunc([
        {pk: 'rgaofijaeoijf', name: '评级', rate: 'S+'},
        {pk: 'rgaofijasfdeoijf', name: 'wode评级', rate: '#+'},
        {pk: 'rgaogsfijaeoijf', name: 'qitade评级', rate: 'S+'},
    ])
}


export function rateRender(item) {
    return `${item.name}: ${item.rate}`
}


// eslint-disable-next-line no-unused-vars
export function authorListGet(query, option, resultFunc) {
    resultFunc([
        {pk: 1, name: '导师1'},
        {pk: 2, name: '导师2'},
        {pk: 3, name: '导师3'},
        {pk: 4, name: `r${query}`}
    ])
}

export function authorRender(item) {
    return `${item.name}`
}

export function topicListGet(query, resultFunc){
    resultFunc([
        {pk: 1, name: '方向1'},
        {pk: 2, name: '方向2'},
        {pk: 3, name: '方向3'},
    ])
}

export function topicRender(item) {
    return `${item.name}`
}

export function topicBuilder(input) {
    var uniqid = require('uniqid');
    return {pk: 'new'+uniqid(), name: input}
}

export function keywordListGet(query, resultFunc){
    resultFunc([
        {pk: 1, name: '关键词1'},
        {pk: 2, name: '关键词2'},
        {pk: 3, name: '关键词3'},
    ])
}

export function keywordRender(item) {
    return `${item.name}`
}

export function keywordBuilder(input) {
    var uniqid = require('uniqid');
    return {pk: 'new'+uniqid() ,name: input}
}

export function projectListGet(query, resultFunc) {
    resultFunc([
        {pk: 1, name: '成果1'},
        {pk: 2, name: '成果1'},
        {pk: 3, name: '成果1'},
    ])
}

export function projectRender(item) {
    return `${item.name}`
}

export function outcomeListGet(query, resultFunc) {
    resultFunc([
        {pk: 1, name: '成果1'},
        {pk: 2, name: '成果2'},
        {pk: 3, name: '成果3'},
    ])
}

export function outcomeRender(item) {
    return `${item.name}`
}

export function typeListGet(query, resultFunc) {
    resultFunc([
        {pk: 1, name: '成果1'},
        {pk: 2, name: '成果1'},
        {pk: 3, name: '成果1'},
    ])
}

export function typeRender(item) {
    return `${item.name}`
}

export function typeBuilder(input) {
    var uniqid = require('uniqid');
    return {pk: 'new'+uniqid(), name: input}
}



export function getDataByUid(input) {
    if (input === 'a00000001') {
        return {
            pk: 'a00000001',
            tjc: 'thesis',
            title: '基于AAA的BBB在CCC上的DDD',
            authors: [{
                pk: 324234,
                name: '小明明',
                in_grade: 2016,
                out_grade: 2020,
                named_mentor: '大明明A',
                proj_mentor: '大明明B'
            }],
            type: {pk: 'type1', name: '理论'},
            date: 1614700800000,
            dup_rate: 23.4,
            intro: '这一次对话的间隔时间很长，字幕有二十分钟没有出现，伊文斯已经从船首踱到船尾了。他看到有一队鱼不断地从海里跃出，在海面上方划出一条在星光下银光闪闪的弧线。几年前，为了考察过度捕捞对沿海物种的影响，他曾经在南中国海的渔船上待过一段时间，渔民们把这种景象叫“龙兵过”，伊文斯现在感觉那很像映在海洋瞳孔上的字幕。这时，他自己眼睛中的字幕也出现了。',
            keywords: [
                {pk: 'k000001', name: 'AAA'},
                {pk: 'k000002', name: 'BBB'},
                {pk: 'k000003', name: 'CCC'},
            ],
            topics: [
                {pk: 't000001', name: 'TTT1'},
                {pk: 't000002', name: 'TTT2'},
            ],
            projects: [
                {pk: 'p123345', name: '基于123345的543321'},
                {pk: 'p234456', name: '基于234456的654432'},
            ],
            outcomes: [
                {pk: 'o12', name: 'o12', type: 'patent'},
            ],
            full_path: '',
            ppt: '',
            favor: false,
        };
    } else if (input === 'a00000002') {
        return {
            pk: 'a00000002',
            tjc: 'journal_paper',
            title: '基于EEE的FFF在GGG上的HHH',
            authors: [
                {pk: '000001', name: '小明明'},
                {pk: '000002', name: '伊文斯'},
                {pk: '000003', name: '大史'}
            ],
            comAuthors: [{pk: '000001', name: '小明明'}],
            journal: {
                pk: 'j123',
                name: '期刊J',
            },
            yvn: {y: 2016, v: 23, n: 45},
            page: 114,
            home: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
            date: 1614700800000,
            intro: '罗辑伸手挥挥，像抚摸天鹅绒般感受着黑暗的质感，“宇宙就是一座黑暗深林，每个文明都是带枪的猎人，像幽灵般潜行于林间，轻轻拨开挡路的树枝，竭力不让脚步发出一点儿声音，连呼吸都小心翼翼，他必须小心，因为林中到处都有与他一样潜行的猎人。如果他发现了别的生命，不管是不是猎人，不管是天使还是恶魔，不管是娇嫩的婴儿还是步履蹒跚的老人，也不管是天仙般的少女还是天神般的男孩，能做的只有一件事：开枪消灭之。在这片深林中，他人就是地狱，就是永恒的威胁，任何暴露自己存在的生命都将很快被消灭。这就是宇宙文明的图景，这就是费米悖论的解释。”',
            keywords: [
                {pk: 'k000004', name: 'DDD'},
                {pk: 'k000005', name: 'EEE'},
                {pk: 'k000006', name: 'FFF'},
            ],
            topics: [
                {pk: 't000003', name: 'TTT3'},
                {pk: 't000002', name: 'TTT2'},
            ],
            joint: {
                isInduct: true,
                isGov: false,
                isInter: false,
                isKua: true,
                inter: false,
            },
            projects: [
                {pk: 'p123345', name: '基于123345的543321'},
                {pk: 'p234456', name: '基于234456的654432'},
            ],
            outcomes: [
                {pk: 'o12', name: 'o12'},
            ],
            favor: true,
        };
    } else if( input === 'a00000003'){
        return {
            pk: 'a00000003',
            tjc: 'conference_paper',
            title: '基于CCC的FFF在GGG上的HHH',
            authors: [
                {pk: '000001', name: '小明明'},
                {pk: '000002', name: '伊文斯'},
                {pk: '000003', name: '大史'}
            ],
            comAuthors: [{pk: '000002', name: '伊文斯'}],
            conference: {
                pk: 'c123',
                name: '会议C',
            },
            conference_year: 2013,
            conference_locate: '波多黎各',
            conference_date: 1614700800000,
            page: 114,
            date: 1614700800000,
            intro: '罗辑伸手挥挥，像抚摸天鹅绒般感受着黑暗的质感，“宇宙就是一座黑暗深林，每个文明都是带枪的猎人，像幽灵般潜行于林间，轻轻拨开挡路的树枝，竭力不让脚步发出一点儿声音，连呼吸都小心翼翼，他必须小心，因为林中到处都有与他一样潜行的猎人。如果他发现了别的生命，不管是不是猎人，不管是天使还是恶魔，不管是娇嫩的婴儿还是步履蹒跚的老人，也不管是天仙般的少女还是天神般的男孩，能做的只有一件事：开枪消灭之。在这片深林中，他人就是地狱，就是永恒的威胁，任何暴露自己存在的生命都将很快被消灭。这就是宇宙文明的图景，这就是费米悖论的解释。”',
            keywords: [
                {pk: 'k000004', name: 'DDD'},
                {pk: 'k000005', name: 'EEE'},
                {pk: 'k000006', name: 'FFF'},
            ],
            topics: [
                {pk: 't000003', name: 'TTT3'},
                {pk: 't000002', name: 'TTT2'},
            ],
            joint: {
                isInduct: true,
                isGov: false,
                isInter: false,
                isKua: true,
                inter: false,
            },
            projects: [
                {pk: 'p123345', name: '基于123345的543321'},
                {pk: 'p234456', name: '基于234456的654432'},
            ],
            outcomes: [
                {pk: 'o12', name: 'o12'},
            ],
            favor: true,
        }
    } else return null;
}

export function getQueryResult() {
    return [
        {
            pk: 'a00000001',
            tjc: 'thesis',
            title: '基于AAA的BBB在CCC上的DDD',
            author: [{pk: '000001', name: '小明明'}],
            date: null,
            intro: '这一次对话的间隔时间很长，字幕有二十分钟没有出现，伊文斯已经从船首踱到船尾了。他看到有一队鱼不断地从海里跃出，在海面上方划出一条在星光下银光闪闪的弧线。几年前，为了考察过度捕捞对沿海物种的影响，他曾经在南中国海的渔船上待过一段时间，渔民们把这种景象叫“龙兵过”，伊文斯现在感觉那很像映在海洋瞳孔上的字幕。这时，他自己眼睛中的字幕也出现了。',
            keywords: [
                {pk: 'k000001', name: 'AAA'},
                {pk: 'k000002', name: 'BBB'},
                {pk: 'k000003', name: 'CCC'},
            ],
            favor: false,
        },
        {
            pk: 'a00000002',
            tjc: 'journal_paper',
            title: '基于EEE的FFF在GGG上的HHH',
            author: [
                {pk: '000001', name: '小明明'},
                {pk: '000002', name: '伊文斯'},
                {pk: '000003', name: '大史'}
            ],
            date: '',
            intro: '罗辑伸手挥挥，像抚摸天鹅绒般感受着黑暗的质感，“宇宙就是一座黑暗深林，每个文明都是带枪的猎人，像幽灵般潜行于林间，轻轻拨开挡路的树枝，竭力不让脚步发出一点儿声音，连呼吸都小心翼翼，他必须小心，因为林中到处都有与他一样潜行的猎人。如果他发现了别的生命，不管是不是猎人，不管是天使还是恶魔，不管是娇嫩的婴儿还是步履蹒跚的老人，也不管是天仙般的少女还是天神般的男孩，能做的只有一件事：开枪消灭之。在这片深林中，他人就是地狱，就是永恒的威胁，任何暴露自己存在的生命都将很快被消灭。这就是宇宙文明的图景，这就是费米悖论的解释。”',
            keywords: [
                {pk: 'k000004', name: 'DDD'},
                {pk: 'k000005', name: 'EEE'},
                {pk: 'k000006', name: 'FFF'},
            ],
            favor: true,
        },
        {
            pk: 'a00000001',
            tjc: 'thesis',
            title: '基于AAA的BBB在CCC上的DDD',
            author: [{pk: '000001', name: '小明明'}],
            date: null,
            intro: '这一次对话的间隔时间很长，字幕有二十分钟没有出现，伊文斯已经从船首踱到船尾了。他看到有一队鱼不断地从海里跃出，在海面上方划出一条在星光下银光闪闪的弧线。几年前，为了考察过度捕捞对沿海物种的影响，他曾经在南中国海的渔船上待过一段时间，渔民们把这种景象叫“龙兵过”，伊文斯现在感觉那很像映在海洋瞳孔上的字幕。这时，他自己眼睛中的字幕也出现了。',
            keywords: [
                {pk: 'k000001', name: 'AAA'},
                {pk: 'k000002', name: 'BBB'},
                {pk: 'k000003', name: 'CCC'},
            ],
            favor: false,
        },
        {
            pk: 'a00000003',
            tjc: 'conference_paper',
            title: '基于EEE的FFF在GGG上的HHH',
            author: [
                {pk: '000001', name: '小明明'},
                {pk: '000002', name: '伊文斯'},
                {pk: '000003', name: '大史'}
            ],
            date: '',
            intro: '罗辑伸手挥挥，像抚摸天鹅绒般感受着黑暗的质感，“宇宙就是一座黑暗深林，每个文明都是带枪的猎人，像幽灵般潜行于林间，轻轻拨开挡路的树枝，竭力不让脚步发出一点儿声音，连呼吸都小心翼翼，他必须小心，因为林中到处都有与他一样潜行的猎人。如果他发现了别的生命，不管是不是猎人，不管是天使还是恶魔，不管是娇嫩的婴儿还是步履蹒跚的老人，也不管是天仙般的少女还是天神般的男孩，能做的只有一件事：开枪消灭之。在这片深林中，他人就是地狱，就是永恒的威胁，任何暴露自己存在的生命都将很快被消灭。这就是宇宙文明的图景，这就是费米悖论的解释。”',
            keywords: [
                {pk: 'k000004', name: 'DDD'},
                {pk: 'k000005', name: 'EEE'},
                {pk: 'k000006', name: 'FFF'},
            ],
            favor: true,
        },
        {
            pk: 'a00000001',
            tjc: 'thesis',
            title: '基于AAA的BBB在CCC上的DDD',
            author: [{pk: '000001', name: '小明明'}],
            date: null,
            intro: '这一次对话的间隔时间很长，字幕有二十分钟没有出现，伊文斯已经从船首踱到船尾了。他看到有一队鱼不断地从海里跃出，在海面上方划出一条在星光下银光闪闪的弧线。几年前，为了考察过度捕捞对沿海物种的影响，他曾经在南中国海的渔船上待过一段时间，渔民们把这种景象叫“龙兵过”，伊文斯现在感觉那很像映在海洋瞳孔上的字幕。这时，他自己眼睛中的字幕也出现了。',
            keywords: [
                {pk: 'k000001', name: 'AAA'},
                {pk: 'k000002', name: 'BBB'},
                {pk: 'k000003', name: 'CCC'},
            ],
            favor: false,
        },
        {
            pk: 'a00000002',
            tjc: 'journal_paper',
            title: '基于EEE的FFF在GGG上的HHH',
            author: [
                {pk: '000001', name: '小明明'},
                {pk: '000002', name: '伊文斯'},
                {pk: '000003', name: '大史'}
            ],
            date: '',
            intro: '罗辑伸手挥挥，像抚摸天鹅绒般感受着黑暗的质感，“宇宙就是一座黑暗深林，每个文明都是带枪的猎人，像幽灵般潜行于林间，轻轻拨开挡路的树枝，竭力不让脚步发出一点儿声音，连呼吸都小心翼翼，他必须小心，因为林中到处都有与他一样潜行的猎人。如果他发现了别的生命，不管是不是猎人，不管是天使还是恶魔，不管是娇嫩的婴儿还是步履蹒跚的老人，也不管是天仙般的少女还是天神般的男孩，能做的只有一件事：开枪消灭之。在这片深林中，他人就是地狱，就是永恒的威胁，任何暴露自己存在的生命都将很快被消灭。这就是宇宙文明的图景，这就是费米悖论的解释。”',
            keywords: [
                {pk: 'k000004', name: 'DDD'},
                {pk: 'k000005', name: 'EEE'},
                {pk: 'k000006', name: 'FFF'},
            ],
            favor: true,
        },
        {
            pk: 'a00000001',
            tjc: 'thesis',
            title: '基于AAA的BBB在CCC上的DDD',
            author: [{pk: '000001', name: '小明明'}],
            date: null,
            intro: '这一次对话的间隔时间很长，字幕有二十分钟没有出现，伊文斯已经从船首踱到船尾了。他看到有一队鱼不断地从海里跃出，在海面上方划出一条在星光下银光闪闪的弧线。几年前，为了考察过度捕捞对沿海物种的影响，他曾经在南中国海的渔船上待过一段时间，渔民们把这种景象叫“龙兵过”，伊文斯现在感觉那很像映在海洋瞳孔上的字幕。这时，他自己眼睛中的字幕也出现了。',
            keywords: [
                {pk: 'k000001', name: 'AAA'},
                {pk: 'k000002', name: 'BBB'},
                {pk: 'k000003', name: 'CCC'},
            ],
            favor: false,
        },
        {
            pk: 'a00000002',
            tjc: 'journal_paper',
            title: '基于EEE的FFF在GGG上的HHH',
            author: [
                {pk: '000001', name: '小明明'},
                {pk: '000002', name: '伊文斯'},
                {pk: '000003', name: '大史'}
            ],
            date: '',
            intro: '罗辑伸手挥挥，像抚摸天鹅绒般感受着黑暗的质感，“宇宙就是一座黑暗深林，每个文明都是带枪的猎人，像幽灵般潜行于林间，轻轻拨开挡路的树枝，竭力不让脚步发出一点儿声音，连呼吸都小心翼翼，他必须小心，因为林中到处都有与他一样潜行的猎人。如果他发现了别的生命，不管是不是猎人，不管是天使还是恶魔，不管是娇嫩的婴儿还是步履蹒跚的老人，也不管是天仙般的少女还是天神般的男孩，能做的只有一件事：开枪消灭之。在这片深林中，他人就是地狱，就是永恒的威胁，任何暴露自己存在的生命都将很快被消灭。这就是宇宙文明的图景，这就是费米悖论的解释。”',
            keywords: [
                {pk: 'k000004', name: 'DDD'},
                {pk: 'k000005', name: 'EEE'},
                {pk: 'k000006', name: 'FFF'},
            ],
            favor: true,
        },
    ];
}
