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

export function journalListGet(query, resultFunc) {
    if (query === undefined) query = '';
    axios.get(`journal_list_get/?q=${query}`,
    )
        .then(function (response) {
            if (response.status === 200) {
                console.info(response.data)
                resultFunc(response.data)
            }
        })
        .catch(function (error) {
            console.log(error)
            resultFunc([])
        })
}

export function journalRender(item) {
    return `${item.name === null ? item.ename : item.name}`
}

export function rateListGet(query, resultFunc) {
    axios.get(`rate_list_get/`,
    )
        .then(function (response) {
            if (response.status === 200) {
                resultFunc(response.data)
            }
        })
        .catch(function (error) {
            console.log(error)
            resultFunc([])
        })
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
        {pk: 4, name: `r+${query}`}
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
    return {name: input}
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
    return {name: input}
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
        {pk: 2, name: '成果1'},
        {pk: 3, name: '成果1'},
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
    return {name: input}
}



export function getDataByUid(input) {
    if (input === 'a00000001') {
        return {
            pk: 'a00000001',
            thesisOrPaper: 'thesis',
            title: '基于AAA的BBB在CCC上的DDD',
            student: {pk: 1, name: '小明明'},
            mentor: {pk: 2, name: '大明明'},
            type: {pk: 'type1', name: '理论'},
            date: null,
            rate: 23.4,
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
                {pk: 'o12', name: 'o12'},
            ],
            favor: false,
        };
    } else if (input === 'a00000002') {
        return {
            pk: 'a00000002',
            thesisOrPaper: 'paper',
            title: '基于EEE的FFF在GGG上的HHH',
            author: [
                {pk: '000001', name: '小明明'},
                {pk: '000002', name: '伊文斯'},
                {pk: '000003', name: '大史'}
            ],
            mentor: {pk: 'm00001', name: '大明明'},
            comAuthors: [{pk: 'm00001', name: '大明明'}],
            journal: {pk: 'j123', name: '期刊J'},
            date: null,
            intro: '罗辑伸手挥挥，像抚摸天鹅绒般感受着黑暗的质感，“宇宙就是一座黑暗深林，每个文明都是带枪的猎人，像幽灵般潜行于林间，轻轻拨开挡路的树枝，竭力不让脚步发出一点儿声音，连呼吸都小心翼翼，他必须小心，因为林中到处都有与他一样潜行的猎人。如果他发现了别的生命，不管是不是猎人，不管是天使还是恶魔，不管是娇嫩的婴儿还是步履蹒跚的老人，也不管是天仙般的少女还是天神般的男孩，能做的只有一件事：开枪消灭之。在这片深林中，他人就是地狱，就是永恒的威胁，任何暴露自己存在的生命都将很快被消灭。这就是宇宙文明的图景，这就是费米悖论的解释。”',
            keywords: [
                {uid: 'k000004', name: 'DDD'},
                {uid: 'k000005', name: 'EEE'},
                {uid: 'k000006', name: 'FFF'},
            ],
            topics: [
                {uid: 't000003', name: 'TTT3'},
                {uid: 't000002', name: 'TTT2'},
            ],
            with: {
                withInduc: true,
                withGov: false,
                withInt: false,
                withInterd: true,
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
    } else return null;
}

export function getQueryResult() {
    return [
        {
            pk: 'a00000001',
            tpc: 'thesis',
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
            tpc: 'journal_paper',
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
            tpc: 'thesis',
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
            tpc: 'journal_paper',
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
            tpc: 'thesis',
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
            tpc: 'journal_paper',
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
            tpc: 'thesis',
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
            tpc: 'journal_paper',
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
