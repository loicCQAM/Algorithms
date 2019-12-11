// LOG320Lab2.cpp : Définit le point d'entrée pour l'application console.
//

#include "stdafx.h"
#include <iostream>
#include <cstdlib>
#include <fstream>
#include <string>
#include <vector>
#include <iterator>
#include <algorithm>
#include <sstream>
using namespace std;

struct pos {
	int h;
	int v;
};

enum eMove {UP,DOWN,LEFT,RIGHT};
const int OUTOFBOUND = 8;
const int DESTROY = 0;
const int PPOS = 2;
const int PILLAR = 1;
const int NBTOEND = 1;

int GetInput();
void DisplayMainMenu();
void DisplayGameMenu();
void PrintLines(vector<string> map);
vector<string> ReadFile(string path);
vector<string> ConvertToPuzzleDisplayFormat(vector<string> puzzleLines);
string AddSeps(const string& str, char sep = ' ');
vector<int> ConvertCharsToInts(const string& str);
vector<vector<int>> ConvertPuzzleToInts(vector<string> puzzleLines);
string ConvertIntsToChars(vector<int> ints);
vector<string> ConvertIntsToPuzzle(vector<vector<int>> ints);
pos GetInitialPos(vector<vector<int>> puzzle);
bool IsPuzzleSolved(vector<vector<int>> puzzle);
bool MoveUp(pos &curPos, vector<vector<int>> &puzzle);
bool MoveDown(pos &curPos, vector<vector<int>> &puzzle);
bool MoveLeft(pos &curPos, vector<vector<int>> &puzzle);
bool MoveRight(pos &curPos, vector<vector<int>> &puzzle);
bool UndoMoveUp(pos &curPos, vector<vector<int>> &puzzle);
bool UndoMoveDown(pos &curPos, vector<vector<int>> &puzzle);
bool UndoMoveLeft(pos &curPos, vector<vector<int>> &puzzle);
bool UndoMoveRight(pos &curPos, vector<vector<int>> &puzzle);
bool ReturnBack(pos &curPos, vector<vector<int>> &puzzle);
bool ChangePos(pos &curPos, vector<vector<int>> &puzzle, eMove move);
int CountApp(vector<vector<int>> puzzle, int value);

int GetInput() 
{
	int choice;
	cin >> choice;
	return choice;
}
void DisplayMainMenu()
{
	cout << "Main Menu" << endl;
	cout << "1 - Solve" << endl;
	cout << "0 - Exit" << endl;
}
void DisplayGameMenu()
{
	cout << "Game Menu" << endl;
	cout << "1 - MoveUp" << endl;
	cout << "2 - MoveDown" << endl;
	cout << "3 - MoveLeft" << endl;
	cout << "4 - MoveRight" << endl;
	cout << "5 - Return" << endl;
	cout << "0 - Exit" << endl;
}
void PrintLines(vector<string> map) 
{
	int i = 0;
	while (i < map.size()) {
		cout << map.at(i) << endl;
		++i;
	}
	cout << endl;
}
vector<string> ReadFile(string path)
{
	vector<string> ctn;
	string str;
	ifstream file(path);
	if (file.is_open()) {
		copy(istream_iterator<string>(file),
			istream_iterator<string>(),
			back_inserter(ctn));
	}
	else cout << "Unable to open file " << path << endl;
	return ctn;
}
vector<string> ConvertToPuzzleDisplayFormat(vector<string> puzzleLines)
{
	string s;
	char des = to_string(DESTROY)[0];
	char ofb = to_string(OUTOFBOUND)[0];
	vector<string> puzzle;
	for (auto &str : puzzleLines) {
		s = str;
		replace(s.begin(), s.end(), des, ofb);
		puzzle.push_back(AddSeps(s));
	}
	return puzzle;
}
string AddSeps(const string& str, char sep)
{
	if (!str.size()) {
		return "";
	}
	stringstream ss;
	ss << str[0];
	for (int i = 1; i < str.size(); i++) {
		ss << sep << str[i];
	}
	return ss.str();
}
vector<int> ConvertCharsToInts(const string& str)
{
	vector<int> ints;
	int number;
	stringstream iss(str);
	while (iss >> number) {
		ints.push_back(number);
	}
	return ints;
}
vector<vector<int>> ConvertPuzzleToInts(vector<string> puzzleLines)
{
	vector<vector<int>> ints;
	for (int i = 0; i < puzzleLines.size(); i++) {
		ints.push_back(ConvertCharsToInts(puzzleLines.at(i)));
	}
	return ints;
}
string ConvertIntsToChars(vector<int> ints) 
{
	stringstream result;
	copy(ints.begin(), ints.end(), ostream_iterator<int>(result, " "));
	return result.str();
}
vector<string> ConvertIntsToPuzzle(vector<vector<int>> ints)
{
	vector<string> puzzle;
	for (int i = 0; i < ints.size(); i++) {
		puzzle.push_back(ConvertIntsToChars(ints.at(i)));
	}
	return puzzle;
}



pos GetInitialPos(vector<vector<int>> puzzle) 
{
	pos curPos;
	curPos.v = -1;
	curPos.h = -1;
	for (int i = 0; i < puzzle.size(); i++) {
		for (int j = 0; j < puzzle[i].size(); j++) {
			if (puzzle[i][j] == PPOS) {
				curPos.v = i;
				curPos.h = j;
				return curPos;
			}
		}
	}
	return curPos;
}
bool IsPuzzleSolved(vector<vector<int>> puzzle)
{
	int count = 0;
	for (int i = 0; i < puzzle.size(); i++) {
		for (int j = 0; j < puzzle[i].size(); j++) {
			if (puzzle[i][j] == PILLAR) {
				count++;
			}
			if (count > NBTOEND) {
				return false;
			}
		}
	}
	cout << "Count: " << count;
	return (count == NBTOEND);
}
bool MoveUp(pos &curPos, vector<vector<int>> &puzzle) 
{
	if (curPos.v - 2 < 0) { return false; }
	if (puzzle[curPos.v - 1][curPos.h] != PILLAR || puzzle[curPos.v - 2][curPos.h] != PILLAR) { return false; }
	puzzle[curPos.v][curPos.h] = PILLAR;
	puzzle[curPos.v - 1][curPos.h] = DESTROY;
	puzzle[curPos.v - 2][curPos.h] = PPOS;
	curPos.v = curPos.v - 2;
	return true;
}
bool MoveDown(pos &curPos, vector<vector<int>> &puzzle)
{
	if (curPos.v + 2 >= puzzle.size()) { return false; }
	if (puzzle[curPos.v + 1][curPos.h] != PILLAR || puzzle[curPos.v + 2][curPos.h] != PILLAR) { return false; }
	puzzle[curPos.v][curPos.h] = PILLAR;
	puzzle[curPos.v + 1][curPos.h] = DESTROY;
	puzzle[curPos.v + 2][curPos.h] = PPOS;
	curPos.v = curPos.v + 2;
	return true;
}
bool MoveLeft(pos &curPos, vector<vector<int>> &puzzle)
{
	if (curPos.h - 2 < 0) { return false; }
	if (puzzle[curPos.v][curPos.h - 1] != PILLAR || puzzle[curPos.v][curPos.h - 2] != PILLAR) { return false; }
	puzzle[curPos.v][curPos.h] = PILLAR;
	puzzle[curPos.v][curPos.h - 1] = DESTROY;
	puzzle[curPos.v][curPos.h - 2] = PPOS;
	curPos.h = curPos.h - 2;
	return true;
}
bool MoveRight(pos &curPos, vector<vector<int>> &puzzle)
{
	if (curPos.h + 2 >= puzzle[0].size()) { return false; }
	if (puzzle[curPos.v][curPos.h + 1] != PILLAR || puzzle[curPos.v][curPos.h + 2] != PILLAR) { return false; }
	puzzle[curPos.v][curPos.h] = PILLAR;
	puzzle[curPos.v][curPos.h + 1] = DESTROY;
	puzzle[curPos.v][curPos.h + 2] = PPOS;
	curPos.h = curPos.h + 2;
	return true;
}
bool UndoMoveUp(pos &curPos, vector<vector<int>> &puzzle)
{
	if (curPos.v + 2 >= puzzle.size()) { return false; }
	if (puzzle[curPos.v + 1][curPos.h] != DESTROY || puzzle[curPos.v + 2][curPos.h] != PILLAR) { return false; }
	puzzle[curPos.v][curPos.h] = PILLAR;
	puzzle[curPos.v + 1][curPos.h] = PILLAR;
	puzzle[curPos.v + 2][curPos.h] = PPOS;
	curPos.v = curPos.v + 2;
}
bool UndoMoveDown(pos &curPos, vector<vector<int>> &puzzle)
{
	if (curPos.v - 2 < 0) { return false; }
	if (puzzle[curPos.v - 1][curPos.h] != DESTROY || puzzle[curPos.v - 2][curPos.h] != PILLAR) { return false; }
	puzzle[curPos.v][curPos.h] = PILLAR;
	puzzle[curPos.v - 1][curPos.h] = PILLAR;
	puzzle[curPos.v - 2][curPos.h] = PPOS;
	curPos.v = curPos.v - 2;
}
bool UndoMoveLeft(pos &curPos, vector<vector<int>> &puzzle)
{
	if (curPos.h + 2 >= puzzle[0].size()) { return false; }
	if (puzzle[curPos.v][curPos.h + 1] != DESTROY || puzzle[curPos.v][curPos.h + 2] != PILLAR) { return false; }
	puzzle[curPos.v][curPos.h] = PILLAR;
	puzzle[curPos.v][curPos.h + 1] = PILLAR;
	puzzle[curPos.v][curPos.h + 2] = PPOS;
	curPos.h = curPos.h + 2;
}
bool UndoMoveRight(pos &curPos, vector<vector<int>> &puzzle)
{
	if (curPos.h - 2 < 0) { return false; }
	if (puzzle[curPos.v][curPos.h - 1] != DESTROY || puzzle[curPos.v][curPos.h - 2] != PILLAR) { return false; }
	puzzle[curPos.v][curPos.h] = PILLAR;
	puzzle[curPos.v][curPos.h - 1] = PILLAR;
	puzzle[curPos.v][curPos.h - 2] = PPOS;
	curPos.h = curPos.h - 2;
}
bool ChangePos(pos &curPos, vector<vector<int>> &puzzle, eMove move)
{
	switch (move)
	{
	case UP:
		if (curPos.v-1 < 0) { return false; }
		if (puzzle[curPos.v][curPos.h] != PPOS || puzzle[curPos.v - 1][curPos.h] != DESTROY) { return false; }
		puzzle[curPos.v][curPos.h] = DESTROY;
		curPos.v = curPos.v - 1;
		puzzle[curPos.v][curPos.h] = PPOS;
		break;
	case DOWN:
		if (curPos.v+1 >= puzzle.size()) { return false; }
		if (puzzle[curPos.v][curPos.h] != PPOS || puzzle[curPos.v + 1][curPos.h] != DESTROY) { return false; }
		puzzle[curPos.v][curPos.h] = DESTROY;
		curPos.v = curPos.v + 1;
		puzzle[curPos.v][curPos.h] = PPOS;
		break;
	case LEFT:
		if (curPos.h-1 < 0) { return false; }
		if (puzzle[curPos.v][curPos.h] != PPOS || puzzle[curPos.v][curPos.h - 1] != DESTROY) { return false; }
		puzzle[curPos.v][curPos.h] = DESTROY;
		curPos.h = curPos.h - 1;
		puzzle[curPos.v][curPos.h] = PPOS;
		break;
	case RIGHT:
		if (curPos.h+1 >= puzzle[0].size()) { return false; }
		if (puzzle[curPos.v][curPos.h] != PPOS || puzzle[curPos.v][curPos.h + 1] != DESTROY) { return false; }
		puzzle[curPos.v][curPos.h] = DESTROY;
		curPos.h = curPos.h + 1;
		puzzle[curPos.v][curPos.h] = PPOS;
		break;
	default:
		break;
	}
}
int CountApp(vector<vector<int>> puzzle, int value)
{
	int count = 0;
	for (int i = 0; i < puzzle.size(); i++) {
		for (int j = 0; j < puzzle[0].size(); j++) {
			if (puzzle[i][j] == value) {
				count++;
			}
		}
	}
	return count;
}
bool stepByStep;
int zerosCountInit = 0;
int nodeCount = 0;
vector<string> gameDisplay;
bool ReturnBack(pos &curPos, vector<vector<int>> &puzzle)
{
	if (stepByStep) {
		int destroyCount;
		int pillarCount;
		int ofbCount;
		int pposCount;
		destroyCount = CountApp(puzzle, DESTROY) - zerosCountInit;
		pillarCount = CountApp(puzzle, PILLAR);
		ofbCount = CountApp(puzzle, OUTOFBOUND);
		pposCount = CountApp(puzzle, PPOS);
		system("cls");
		gameDisplay = ConvertIntsToPuzzle(puzzle);
		PrintLines(gameDisplay);
		cout << "NODECOUNT " << nodeCount << endl;
		cout << "DESTROY " << destroyCount << " PILLAR " << pillarCount << " OUTOFBOUND " << ofbCount << " PPOS " << pposCount << " = "  << destroyCount + pillarCount + ofbCount + pposCount << endl;
		system("pause");
	}

	if (IsPuzzleSolved(puzzle)) { return true; }
	nodeCount++;
	if (MoveUp(curPos, puzzle)) {
		if (ReturnBack(curPos, puzzle)) { return true; }
		else {
			if (ChangePos(curPos, puzzle, DOWN)) {
				if (ReturnBack(curPos, puzzle)) { return true; }
				else { ChangePos(curPos, puzzle, UP); }
			}
			if (!UndoMoveUp(curPos, puzzle)) { return false; }
		}
	}
	if (MoveLeft(curPos, puzzle)) {
		if (ReturnBack(curPos, puzzle)) { return true; }
		else {
			if (ChangePos(curPos, puzzle, RIGHT)) {
				if (ReturnBack(curPos, puzzle)) { return true; }
				else { ChangePos(curPos, puzzle, LEFT); }
			}
			if (!UndoMoveLeft(curPos, puzzle)) { return false; }
		}
	}
	if (MoveDown(curPos, puzzle)) {
		if (ReturnBack(curPos, puzzle)) { return true; }
		else {
			if (ChangePos(curPos, puzzle, UP)) {
				if (ReturnBack(curPos, puzzle)) { return true; }
				else { ChangePos(curPos, puzzle, DOWN); }
			}
			if (!UndoMoveDown(curPos, puzzle)) { return false; }
		}
	}
	if (MoveRight(curPos, puzzle)) {
		if (ReturnBack(curPos, puzzle)) { return true; }
		else {
			if (ChangePos(curPos, puzzle, LEFT)) {
				if (ReturnBack(curPos, puzzle)) { return true; }
				else { ChangePos(curPos, puzzle, RIGHT); }
			}
			if (!UndoMoveRight(curPos, puzzle)) { return false; }
		}
	}
	return false;
}





int main(int argc, const char* argv[])
{
	vector<string> f;
	f = ReadFile(argv[1]);
	vector<string> strf;
	strf = ConvertToPuzzleDisplayFormat(f);
	int choice;
	do
	{
		system("cls");
		DisplayMainMenu();
		choice = GetInput();
		string stepQ;
		
		vector<vector<int>> puzzle;
		pos curPos;

		switch (choice)
		{
		case 0:
			cout << "Bye!" << endl;
			break;
		case 1:
			nodeCount = 0;
			puzzle = ConvertPuzzleToInts(strf);
			curPos = GetInitialPos(puzzle);
			if (curPos.h < 0) { 
				cout << "No initial position found!" << endl;
				break; 
			}
			zerosCountInit = CountApp(puzzle, 0);
			gameDisplay = ConvertIntsToPuzzle(puzzle);
			PrintLines(gameDisplay);
			cout << "Do you want to follow step by step: [y/n]" << endl;
			cin >> stepQ;
			stepByStep = (stepQ == "y");
			if (ReturnBack(curPos, puzzle)) {
				system("cls");
				gameDisplay = ConvertIntsToPuzzle(puzzle);
				PrintLines(gameDisplay);
				cout << "SOLVED " << nodeCount << endl;
				system("pause");
			} else {
				int destroyCount;
				int pillarCount;
				int ofbCount;
				int pposCount;
				destroyCount = CountApp(puzzle, DESTROY) - zerosCountInit;
				pillarCount = CountApp(puzzle, PILLAR);
				ofbCount = CountApp(puzzle, OUTOFBOUND);
				pposCount = CountApp(puzzle, PPOS);
				system("cls");
				gameDisplay = ConvertIntsToPuzzle(puzzle);
				PrintLines(gameDisplay);
				cout << "FAILURE " << nodeCount << endl;
				cout << "DESTROY " << destroyCount << " PILLAR " << pillarCount << " OUTOFBOUND " << ofbCount << " PPOS " << pposCount << " = " << destroyCount + pillarCount + ofbCount + pposCount << endl;
				system("pause");
			}
			break;
		default:
			break;
		}
	} while (choice != 0);

	system("pause");
	return EXIT_SUCCESS;
}