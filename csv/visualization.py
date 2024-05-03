import os.path

import matplotlib.pyplot as plt
import pandas as pd


def main(filenames: list[str], format_data_img: str, format_img: str, dir_name: str) -> None:
    for filename in filenames:
        df = pd.read_csv(
            f"{filename}.{format_data_img}",
            header=None,
            names=["x", "y"],
        )
        plt.title(f"y = {filename}(x)")
        plt.xlabel("x")
        plt.ylabel("y")
        plt.grid()
        plt.plot(df["x"], df["y"])
        plt.savefig(os.path.join(dir_name, f"{filename}.{format_img}"), format=format_img)
        plt.close()


if __name__ == '__main__':
    FORMAT_IMG: str = 'png'
    FORMAT_DATA_FILE: str = 'csv'
    FILENAMES: list[str] = [
        "cos",
        "cot",
        "function_system",
        "ln",
        "log2",
        "log5",
        "log10",
        "sec",
        "sin",
        "tan"
    ]
    DIR_NAME: str = 'img'
    main(FILENAMES, FORMAT_DATA_FILE, FORMAT_IMG, DIR_NAME)
