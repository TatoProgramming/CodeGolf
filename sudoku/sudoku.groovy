class Node{
    int value
    Node(v){ value = v }
    String toString(){ return value }
}

class Game {
    def boxes = [], rows = [], cols = []
    def board = [
            [new Node(5), new Node(3), new Node(0), new Node(0), new Node(7), new Node(0), new Node(0), new Node(0), new Node(0)],
            [new Node(6), new Node(0), new Node(0), new Node(1), new Node(9), new Node(5), new Node(0), new Node(0), new Node(0)],
            [new Node(0), new Node(9), new Node(8), new Node(0), new Node(0), new Node(0), new Node(0), new Node(6), new Node(0)],
            [new Node(8), new Node(0), new Node(0), new Node(0), new Node(6), new Node(0), new Node(0), new Node(0), new Node(3)],
            [new Node(4), new Node(0), new Node(0), new Node(8), new Node(0), new Node(3), new Node(0), new Node(0), new Node(1)],
            [new Node(7), new Node(0), new Node(0), new Node(0), new Node(2), new Node(0), new Node(0), new Node(0), new Node(6)],
            [new Node(0), new Node(6), new Node(0), new Node(0), new Node(0), new Node(0), new Node(2), new Node(8), new Node(0)],
            [new Node(0), new Node(0), new Node(0), new Node(4), new Node(1), new Node(9), new Node(0), new Node(0), new Node(5)],
            [new Node(0), new Node(0), new Node(0), new Node(0), new Node(8), new Node(0), new Node(0), new Node(7), new Node(9)],
    ]


    Game(){
//        def s = (new File('sudoku/1.s').getText())
//        s.eachLine {
//            def l = s.split(',')
//            def r = []
//            l.each {
//                print(it)
//                r.add(it as int)
//            }
//            board.add(r)
//        }

        setupBoxes()
        setupRowsAndCols()
    }

    def setupBoxes() {
        [
                [1, 1], [1, 4], [1, 7],
                [4, 1], [4, 4], [4, 7],
                [7, 1], [7, 4], [7, 7]
        ].each {
            boxes.add(setupBox(it[0], it[1]))
        }
    }

    def setupBox(int i, int j) {
        def box = []
        box.add(board[i - 1][j - 1]); box.add(board[i - 1][j]); box.add(board[i - 1][j + 1]);
        box.add(board[i][j - 1]); box.add(board[i][j]); box.add(board[i][j + 1]);
        box.add(board[i + 1][j - 1]); box.add(board[i + 1][j]); box.add(board[i + 1][j + 1]);
        return box
    }

    def setupRowsAndCols(){
        def row =[], col = []
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                row.add(board[i][j])
                col.add(board[j][i])
            }
            rows.add(row)
            cols.add(col)
            row = []
            col = []
        }
    }

    def solve(){
        def run = true
        while(run){
            run = false
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.size(); j++) {
                    if(board[i][j].value == 0){
                        def row = rows[i]*.value.toSet()
                        def col = cols[j]*.value.toSet()
                        int bc = j/3, br = i/3
                        def box = boxes[br*3+bc]*.value.toSet()
                        def pos = ([1,2,3,4,5,6,7,8,9]).toSet() - row - col - box
                        if(pos.size() == 1) {
                            board[i][j].value = pos[0]
                            run = true
                        }
                    }
                }
            }
        }
    }
}

Game game = new Game()
game.solve()
game.board.each {println(it)}


