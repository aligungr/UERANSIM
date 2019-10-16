import { ConsoleUI } from '../components/consoleUI'

export class konsol {

  public static log(text: string, tag: string | null = null) {
    const entry =
      '[' +
      this.getDateTime() +
      '] ' +
      (tag == null || tag.length === 0 ? '' : tag + ' | ') +
      text

    const instance = ConsoleUI.instance
    if (instance != null) {
      instance.appendText(entry, 'log-text-normal')
    } else {
      alert('Console not ready yet')
      document.documentElement.innerText = entry
    }
  }

  public static success(text: string, tag: string | null = null) {
    const entry =
      '[' +
      this.getDateTime() +
      '] ' +
      (tag == null || tag.length === 0 ? '' : tag + ' | ') +
      text

    const instance = ConsoleUI.instance
    if (instance != null) {
      instance.appendText(entry, 'log-text-success')
    } else {
      alert('Console not ready yet')
      document.documentElement.innerText = entry
    }
  }

  public static error(text: string, tag: string | null = null) {
    const entry =
      '[' +
      this.getDateTime() +
      '] ' +
      (tag == null || tag.length === 0 ? '' : tag + ' | ') +
      text

    const instance = ConsoleUI.instance
    if (instance != null) {
      instance.appendText(entry, 'log-text-error')
    } else {
      alert('Console not ready yet')
      document.documentElement.innerText = entry
    }
  }

  public static warn(text: string, tag: string | null = null) {
    const entry =
      '[' +
      this.getDateTime() +
      '] ' +
      (tag == null || tag.length === 0 ? '' : tag + ' | ') +
      text

    const instance = ConsoleUI.instance
    if (instance != null) {
      instance.appendText(entry, 'log-text-warning')
    } else {
      alert('Console not ready yet')
      document.documentElement.innerText = entry
    }
  }

  public static clear() {
    ConsoleUI.instance.clear()
  }

  private static getDateTime(): string {
    const date = new Date()
    return (
      date
        .getHours()
        .toString()
        .padStart(2, '0') +
      ':' +
      date
        .getMinutes()
        .toString()
        .padStart(2, '0') +
      ':' +
      date
        .getSeconds()
        .toString()
        .padStart(2, '0') +
      ':' +
      date
        .getMilliseconds()
        .toString()
        .padStart(4, '0')
    )
  }
}
