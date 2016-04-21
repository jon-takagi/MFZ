/**
 * Created by 40095 on 4/20/16.
 */
public class Company {
    int stations;
    int scorePerAsset = 5;
    Frame[] frames;
    int activeFrames;
    int totalSystems;
    int numOfFrames;

    Company(Company[] competitors) {
        scorePerAsset = calcScorePerAsset(competitors);
    }
    Company() {
        scorePerAsset = 5;
    }

    void buildFrames() {
        frames = new Frame[numOfFrames];
    }

    int calcScorePerAsset(Company[] competitors) {
        int minNumOfFrames = numOfFrames;
        int maxNumOfFrames = numOfFrames;
        int minTotalSystems = totalSystems;
        int maxTotalSystems = totalSystems;
        for (int i = 0; i < competitors.length; i++) {
            if (minNumOfFrames > competitors[i].numOfFrames)
                minNumOfFrames = competitors[i].numOfFrames;
            if (maxNumOfFrames < competitors[i].numOfFrames)
                maxNumOfFrames = competitors[i].numOfFrames;
            if (minTotalSystems > competitors[i].totalSystems)
                minTotalSystems = competitors[i].totalSystems;
            if (maxTotalSystems < competitors[i].totalSystems)
                maxTotalSystems = competitors[i].totalSystems;
        }
        if (minNumOfFrames == numOfFrames)
            scorePerAsset += 1;
        if (maxNumOfFrames == numOfFrames)
            scorePerAsset -= 1;
        if (minTotalSystems == totalSystems)
            scorePerAsset += 1;
        if (maxTotalSystems == totalSystems)
            scorePerAsset -= 1;

        return scorePerAsset;
    }

    int getScore() {
        activeFrames = 0;
        totalSystems = 0;
        for (int i = 0; i < frames.length; i++) {
            if (frames[i].isActive()) {
                totalSystems += frames[i].frameSystems;
                activeFrames++;
            }
        }

        return scorePerAsset * (activeFrames + stations);
    }
}
