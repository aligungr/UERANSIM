import { create } from 'zustand'

export enum ContentType {
  FLOW_SELECTION = 1,
  FLOW_ACTION = 2,
}

export const [useContentStore] = create<ContentStore>(set => ({
  contentType: ContentType.FLOW_SELECTION,
  setContent: (contentType: ContentType) =>
    set(state => ({
      contentType: contentType,

    })),
}))

type ContentStore = {
  contentType: ContentType,
  setContent(contentType: ContentType): void
}